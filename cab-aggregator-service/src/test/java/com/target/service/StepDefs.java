package com.target.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


/**
 * StepDefs class.
 * @author Rohan Raju
 *
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@EnableFeignClients(value = "com.target.client")
@ActiveProfiles("integration-test")
public abstract class StepDefs {
}

