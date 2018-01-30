package com.target.intf;

/**
 * Created by rohan on 30/12/17.
 */
public class TestResponseDTO {

    private long testId;
    private String testCode;
    private String testName;

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

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
