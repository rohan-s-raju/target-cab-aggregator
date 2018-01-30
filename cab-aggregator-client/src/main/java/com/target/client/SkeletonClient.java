package com.target.client;

import com.target.client.config.SkeletonFeignConfiguration;
import com.target.intf.TestRequestDTO;
import com.target.intf.TestResponseDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "skeleton-service", configuration = SkeletonFeignConfiguration.class, url = "${cab-service.url}")
public interface SkeletonClient {

    @RequestMapping(value = "/test/create", method = RequestMethod.POST)
    TestResponseDTO create(TestRequestDTO testRequestDTO) throws SkeletonClientException;

    @RequestMapping(value = "/test/all", method = RequestMethod.GET)
    List<TestResponseDTO> getAllTest() throws SkeletonClientException;

}
