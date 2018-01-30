package com.target.impl.application.service;

import com.target.intf.CabRequestDTO;
import com.target.intf.RegisterCabRequestDTO;
import com.target.intf.RoutePlanResponseDTO;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by rohan on 17/1/18.
 */
public interface ICabService {
    void createDropPoint(LinkedHashMap<String, String> dropPointMap);

    void registerCabForTeamMember(RegisterCabRequestDTO registerCabRequestDTO);

    void createCabs(List<CabRequestDTO> cabRequestDTOS);

    RoutePlanResponseDTO fetchRoutePlanFromHeadQuarter();
}
