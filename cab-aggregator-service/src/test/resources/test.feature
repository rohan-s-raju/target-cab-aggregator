Feature: Add A Test

  Scenario: Test CRUD operation
    Given the following Add Test Request data:
      | TestCode | TestName     |
      | TestG1   | Rocking Test |
      | TestG2   | Test    Test |
    When call the add Test Client
    And the all use find all Test client call to check data is present
    And the added Test request must be present in database

  Scenario: Test validation
    Given the following Add Test Request data:
      | TestCode | TestName     |
      |          | Rocking Test |
      | TestG2   |              |
      | TestG3   | Test    Test |
    When call the add Test Client
    Then the following errors should match:
      | errorCode                       | errorMessage                    |
      | TEST_CODE_IS_NOT_NULL           | test code is not null           |
      | TEST_NAME_IS_NOT_NULL           | test name is not null           |


