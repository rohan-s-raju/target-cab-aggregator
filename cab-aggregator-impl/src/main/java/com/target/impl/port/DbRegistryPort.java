package com.target.impl.port;

import com.target.impl.port.adaptor.DataBaseRegistration;
import com.target.impl.port.adaptor.persistance.repository.CabRepository;
import com.target.impl.port.adaptor.persistance.repository.DropPointRepository;
import com.target.impl.port.adaptor.persistance.repository.TeamMemberRepository;

/**
 * Created by rohan on 18/1/18.
 */
public class DbRegistryPort {

    private static DropPointRepository dropPointRepository;
    private static TeamMemberRepository teamMemberRepository;
    private static CabRepository cabRepository;

    private DbRegistryPort(){
    }

    public static void register(DataBaseRegistration dataBaseRegistration) {
        dropPointRepository = dataBaseRegistration.getDropPointRepository();
        teamMemberRepository = dataBaseRegistration.getTeamMemberRepository();
        cabRepository =dataBaseRegistration.getCabRepository();
    }

    public static CabRepository getCabRepository() {
        return cabRepository;
    }

    public static TeamMemberRepository getTeamMemberRepository() {
        return teamMemberRepository;
    }

    public static DropPointRepository getDropPointRepository() {
        return dropPointRepository;
    }
}
