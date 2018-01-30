package com.target.intf;

import javax.validation.constraints.NotNull;

/**
 * Created by rohan on 30/12/17.
 */
public class TestRequestDTO {

    @NotNull(message =  "TEST_CODE_IS_NOT_NULL")
    private String testCode;

    @NotNull(message = "TEST_NAME_IS_NOT_NULL")
    private String testName;

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
