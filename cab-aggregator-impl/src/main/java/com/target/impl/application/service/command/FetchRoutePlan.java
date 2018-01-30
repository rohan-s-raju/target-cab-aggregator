package com.target.impl.application.service.command;

import com.target.impl.domain.entity.Cab;
import com.target.impl.domain.entity.DropPoint;
import com.target.impl.domain.entity.Gender;
import com.target.impl.domain.entity.TeamMember;
import com.target.impl.error.ErrorEnum;
import com.target.impl.exception.BusinessException;
import com.target.impl.port.DbRegistryPort;
import com.target.intf.Route;
import com.target.intf.RoutePlanResponseDTO;
import com.target.intf.ErrorDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by rohan on 18/1/18.
 */
public class FetchRoutePlan {

    public static final String TARGET_HEADQUARTER = "target_headquarter";

    public RoutePlanResponseDTO fetchRoutePlanFromHeadQuarter() {
        List<Cab> cabList = DbRegistryPort.getCabRepository().findAll();

        List<TeamMember> allTeamMember = DbRegistryPort.getTeamMemberRepository().findAll();

        List<TeamMember> maleTeamMember = getTeamMemberByGender(allTeamMember, Gender.M);
        List<TeamMember> femaleTeamMember = getTeamMemberByGender(allTeamMember, Gender.F);

        List<TeamMember> processedTeamMembers = new ArrayList<>();

        List<Route> routes = new ArrayList<>();

        for (Cab cab : cabList) {

            long capacity = cab.getCapacity();
            long distance = 0;

            Set<String> rotues = new HashSet<>(Arrays.asList(TARGET_HEADQUARTER));
            String previousDropPoint = TARGET_HEADQUARTER;
            List<String> teamMemberId = new ArrayList<>();
            List<String> processedDropPoint = new ArrayList<>();

            for (TeamMember femaleMember : femaleTeamMember) {
                if (!processedTeamMembers.contains(femaleMember) && capacity > 1) {
                    teamMemberId.add(String.valueOf(femaleMember.getTeamMemberID()));
                    processedTeamMembers.add(femaleMember);
                    if(!previousDropPoint.equalsIgnoreCase(femaleMember.getDropPoint())) {
                        rotues.add(femaleMember.getDropPoint());
                        distance += getDistance(previousDropPoint, femaleMember.getDropPoint(), processedDropPoint);
                    }
                    previousDropPoint = femaleMember.getDropPoint();
                    capacity --;

                }
            }

            for (TeamMember maleMember : maleTeamMember) {
                if (!processedTeamMembers.contains(maleMember) && capacity > 0) {
                    teamMemberId.add(String.valueOf(maleMember.getTeamMemberID()));
                    processedTeamMembers.add(maleMember);
                    if(!previousDropPoint.equalsIgnoreCase(maleMember.getDropPoint())) {
                        rotues.add(maleMember.getDropPoint());
                        distance += getDistance(previousDropPoint, maleMember.getDropPoint(), processedDropPoint);
                    }
                    previousDropPoint = maleMember.getDropPoint();
                    capacity --;
                }
            }
            Route route = new Route();
            route.setCabId(cab.getCabId());
            route.setRoute(String.join(",", rotues));
            route.setTeamMemberIds(String.join("," ,teamMemberId));
            route.setRouteCost(distance * cab.getCost());
            routes.add(route);

        }

        RoutePlanResponseDTO routePlanResponseDTO = new RoutePlanResponseDTO();
        routePlanResponseDTO.setTotalCost(routes.stream().mapToLong(Route::getRouteCost).sum());
        routePlanResponseDTO.setRoutes(routes);
        return routePlanResponseDTO;
    }

    private long getDistance(String from, String to, List<String> processedDropPoint) {
        if (!processedDropPoint.contains(to)) {
            DropPoint dropPoint = DbRegistryPort.getDropPointRepository().findByFromAndTo(from, to)
                    .orElseThrow(() -> new BusinessException(new ErrorDTO(ErrorEnum.DROP_POINT_NOT_FOUND.name(), ErrorEnum.DROP_POINT_NOT_FOUND.getErrorMessage())));
            processedDropPoint.add(to);
            return dropPoint.getDistance();
        }
        return 0l;
    }

    private List<TeamMember> getTeamMemberByGender(List<TeamMember> allTeamMember, Gender gender) {
        return allTeamMember.stream().filter(teamMember -> teamMember.getGender() == gender).collect(Collectors.toList());
    }
}
