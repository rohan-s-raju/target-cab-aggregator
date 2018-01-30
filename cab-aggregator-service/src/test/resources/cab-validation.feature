Feature: Cab Aggregator validation Test

  Scenario: Validation 1 for drop Point With InvalidHeader
    Given the following Drop Point data:
      | target_headquar | 1,8,1,2,1 |
      | pointA          | 0,1,2,1,2 |
      | pointB          | 8,0,1,3,1 |
      | pointC          | 7,9,0,1,1 |
      | pointD          | 2,2,2,0,1 |
      | pointE          | 2,9,6,7,0 |
    When call the add Drop Point endpoint
    Then the following cab errors should match:
      | errorCode                              | errorMessage                           |
      | DROP_POINT_HEAD_QUARTER_IS_NOT_PRESENT | drop_point_head_quarter_is_not_present |


  Scenario: Validation 1 for drop Point With Invalid data
    Given the following Drop Point data:
      | target_headquarter | 1,8,1,2,1 |
      | pointA             | 1,1,2,1,2 |
      | pointB             | 8,0,1,3,1 |
      | pointC             | 7,9,0,1,1 |
      | pointD             | 2,2,2,0,1 |
      | pointE             | 2,9,6,7,0 |
    When call the add Drop Point endpoint
    Then the following cab errors should match:
      | errorCode                             | errorMessage                                 |
      | DROP_POINT_INPUT_DATA_IS_NOT_VALID_AT | drop_point_input_data_is_not_valid_at pointA |

  Scenario: Drop point Not present for Team member
    Given the following Drop Point data:
      | target_headquarter | 1,8,1,2,1 |
      | pointA             | 0,1,2,1,2 |
      | pointB             | 8,0,1,3,1 |
      | pointC             | 7,9,0,1,1 |
      | pointD             | 2,2,2,0,1 |
      | pointE             | 2,9,6,7,0 |
    And the following Team Member data:
      | TeamMemberID | Gender | droppoint |
      | 0            | M      | pointZ    |
    When call the add Drop Point endpoint
    And call the add Team Member endpoint

    Then the following cab errors should match:
      | errorCode            | errorMessage         |
      | DROP_POINT_NOT_FOUND | drop_point_not_found |

  Scenario: Cab capacity Not available
    Given the following Drop Point data:
      | target_headquarter | 1,8,1,2,1 |
      | pointA             | 0,1,2,1,2 |
      | pointB             | 8,0,1,3,1 |
      | pointC             | 7,9,0,1,1 |
      | pointD             | 2,2,2,0,1 |
      | pointE             | 2,9,6,7,0 |
    And the following Team Member data:
      | TeamMemberID | Gender | droppoint |
      | 0            | M      | pointA    |
      | 1            | F      | pointB    |
    And the following Cab data:
      | cabId | capapcity | cost |
      | 1     | 1         | 1    |
    When call the add Drop Point endpoint
    And call the add Team Member endpoint
    And call the add Cab endPoint
    Then the following cab errors should match:
      | errorCode                  | errorMessage               |
      | CAB_CAPACITY_DID_NOT_MATCH | cab_capacity_did_not_match |



