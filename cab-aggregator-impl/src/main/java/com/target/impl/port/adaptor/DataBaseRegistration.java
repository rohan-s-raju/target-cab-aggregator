package com.target.impl.port.adaptor;

import com.target.impl.port.DbRegistryPort;
import com.target.impl.port.adaptor.persistance.repository.CabRepository;
import com.target.impl.port.adaptor.persistance.repository.DropPointRepository;
import com.target.impl.port.adaptor.persistance.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rohan on 18/1/18.
 */
@Service
public class DataBaseRegistration {

    private final DropPointRepository dropPointRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final CabRepository cabRepository;

    @Autowired
    public DataBaseRegistration(DropPointRepository dropPointRepository, TeamMemberRepository teamMemberRepository, CabRepository cabRepository) {
        this.dropPointRepository = dropPointRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.cabRepository = cabRepository;
        DbRegistryPort.register(this);
    }

    public DropPointRepository getDropPointRepository() {
        return dropPointRepository;
    }

    public TeamMemberRepository getTeamMemberRepository() {
        return teamMemberRepository;
    }

    public CabRepository getCabRepository() {
        return cabRepository;
    }
}
