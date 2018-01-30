package com.target.impl.application.service;

import com.target.impl.domain.entity.Test;
import com.target.impl.port.adaptor.persistance.repository.TestRepository;
import com.target.intf.TestRequestDTO;
import com.target.intf.TestResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public TestResponseDTO createTest(TestRequestDTO testRequestDTO){
        Test test = new Test.Builder().testName(testRequestDTO.getTestName()).testCode(testRequestDTO.getTestCode()).createdBy("test").updatedBy("test").createdDate(new Date()).updatedDate(new Date()).build();
        return getTestResponseDTO(test);
    }

    private TestResponseDTO getTestResponseDTO(Test test) {
        Test saveTest = testRepository.save(test);
        TestResponseDTO testResponseDTO = new TestResponseDTO();
        testResponseDTO.setTestId(saveTest.getTestId());
        testResponseDTO.setTestCode(saveTest.getTestCode());
        testResponseDTO.setTestName(saveTest.getTestName());
        return testResponseDTO;
    }

    @Override
    public List<TestResponseDTO> getAllTest() {
        return testRepository.findAll().stream().map(this::getTestResponseDTO).collect(Collectors.toList());
    }
}
