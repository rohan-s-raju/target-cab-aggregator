package com.target.impl.application.service;

import com.target.impl.application.service.command.CreateCabs;
import com.target.impl.application.service.command.FetchRoutePlan;
import com.target.impl.application.service.command.RegisterCab;
import com.target.impl.application.service.command.CreateDropPoint;
import com.target.intf.CabRequestDTO;
import com.target.intf.RegisterCabRequestDTO;
import com.target.intf.RoutePlanResponseDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by rohan on 17/1/18.
 */
@Service
public class CabService implements ICabService {

    @Override
    public void createDropPoint(LinkedHashMap<String, String> dropPointMap) {
        CreateDropPoint createDropPoint = new CreateDropPoint();
        createDropPoint.create(dropPointMap);
    }

    @Override
    public void registerCabForTeamMember(RegisterCabRequestDTO registerCabRequestDTO) {
        RegisterCab registerCab = new RegisterCab();
        registerCab.register(registerCabRequestDTO);
    }

    @Override
    public void createCabs(List<CabRequestDTO> cabRequestDTOS) {
        CreateCabs createCabs = new CreateCabs();
        createCabs.create(cabRequestDTOS);
    }

    @Override
    public RoutePlanResponseDTO fetchRoutePlanFromHeadQuarter() {
        FetchRoutePlan fetchRoutePlan = new FetchRoutePlan();
        return fetchRoutePlan.fetchRoutePlanFromHeadQuarter();
    }
}
