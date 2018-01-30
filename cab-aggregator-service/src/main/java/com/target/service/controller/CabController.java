package com.target.service.controller;

import com.target.impl.application.service.ICabService;
import com.target.intf.CabRequestDTO;
import com.target.intf.RegisterCabRequestDTO;
import com.target.intf.RoutePlanResponseDTO;
import com.target.service.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by rohan on 17/1/18.
 */
@RestController
public class CabController extends BaseController {

    private final ICabService iCabService;

    @Autowired
    public CabController(ICabService iCabService) {
        this.iCabService = iCabService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCabForTeamMember(@RequestBody @Valid RegisterCabRequestDTO registerCabRequestDTO, BindingResult bindingResult) {
        checkForValidityOfRequest(bindingResult);
        iCabService.registerCabForTeamMember(registerCabRequestDTO);
    }

    @RequestMapping(value = "/cabs", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCab(@RequestBody List<CabRequestDTO> cabRequestDTOS) {
        iCabService.createCabs(cabRequestDTOS);
    }

    @RequestMapping(value = "/drop_points", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDropPoint(@RequestBody LinkedHashMap<String,String> DropPointMap) {
        iCabService.createDropPoint(DropPointMap);
    }

    @RequestMapping(value = "/route_plan", method = RequestMethod.GET)
    public RoutePlanResponseDTO fetchRoutePlanFromHeadQuarter() {
        return iCabService.fetchRoutePlanFromHeadQuarter();
    }
}
