package com.target.service.controller;

import com.target.impl.application.service.ITestService;
import com.target.intf.TestRequestDTO;
import com.target.intf.TestResponseDTO;
import com.target.service.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rohan on 30/12/17.
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final ITestService iTestService;

    @Autowired
    public TestController(ITestService iTestService) {
        this.iTestService = iTestService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TestResponseDTO create(@RequestBody @Valid TestRequestDTO testRequestDTO, BindingResult bindingResult) {
        checkForValidityOfRequest(bindingResult);
        return iTestService.createTest(testRequestDTO);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<TestResponseDTO> getAllTest(){
        return iTestService.getAllTest();
    }


}
