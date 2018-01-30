package com.target.impl.application.service;


import com.target.intf.TestRequestDTO;
import com.target.intf.TestResponseDTO;

import java.util.List;

/**
 * Created by rohan on 30/12/17.
 */
public interface ITestService {
    TestResponseDTO createTest(TestRequestDTO testRequestDTO);

    List<TestResponseDTO> getAllTest();
}
