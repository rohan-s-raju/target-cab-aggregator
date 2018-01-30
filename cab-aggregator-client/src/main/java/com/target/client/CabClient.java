package com.target.client;

import com.target.client.config.SkeletonFeignConfiguration;
import com.target.intf.CabRequestDTO;
import com.target.intf.RegisterCabRequestDTO;
import com.target.intf.RoutePlanResponseDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.List;

@FeignClient(name = "cab-service", configuration = SkeletonFeignConfiguration.class, url = "${cab-service.url}")
public interface CabClient {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void createCabForTeamMember(RegisterCabRequestDTO registerCabRequestDTO) throws SkeletonClientException;

    @RequestMapping(value = "/cabs", method = RequestMethod.POST)
    public void createCab(@RequestBody List<CabRequestDTO> cabRequestDTOS) throws SkeletonClientException;

    @RequestMapping(value = "/drop_points", method = RequestMethod.POST)
    public void createDropPoint(@RequestBody LinkedHashMap<String, String> DropPointMap) throws SkeletonClientException;

    @RequestMapping(value = "/route_plan", method = RequestMethod.GET)
    public RoutePlanResponseDTO fetchRoutePlanFromHeadQuarter() throws SkeletonClientException;
}
