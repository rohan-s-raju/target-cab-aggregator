Feature: Cab Aggregator Test

  Scenario: Test Cab
    Given the following Drop Point data:
      | target_headquarter | 1,8,1,2,1 |
      | pointA             | 0,1,2,1,2 |
      | pointB             | 8,0,1,3,1 |
      | pointC             | 7,9,0,1,1 |
      | pointD             | 2,2,2,0,1 |
      | pointE             | 2,9,6,7,0 |
    And the following Team Member data:
      | TeamMemberID | Gender | droppoint |
      | 0            | M      | pointD    |
      | 1            | F      | pointA    |
      | 2            | M      | pointB    |
      | 3            | F      | pointC    |
      | 5            | M      | pointB    |
    And the following Cab data:
      | cabId | capapcity | cost |
      | 1     | 2         | 1    |
      | 2     | 3         | 2    |
    When call the add Drop Point endpoint
    And call the add Team Member endpoint
    And call the add Cab endPoint
    And call the add Fetch endPoint
    Then the drop Point table should have:
      | pointA             | pointB | 1 |
      | pointA             | pointC | 2 |
      | pointA             | pointD | 1 |
      | pointA             | pointE | 2 |
      | pointB             | pointA | 8 |
      | pointB             | pointC | 1 |
      | pointB             | pointD | 3 |
      | pointB             | pointE | 1 |
      | pointC             | pointA | 7 |
      | pointC             | pointB | 9 |
      | pointC             | pointD | 1 |
      | pointC             | pointE | 1 |
      | pointD             | pointA | 2 |
      | pointD             | pointB | 2 |
      | pointD             | pointC | 2 |
      | pointD             | pointE | 1 |
      | pointE             | pointA | 2 |
      | pointE             | pointB | 9 |
      | pointE             | pointC | 6 |
      | pointE             | pointD | 7 |
      | target_headquarter | pointA | 1 |
      | target_headquarter | pointB | 8 |
      | target_headquarter | pointC | 1 |
      | target_headquarter | pointD | 2 |
      | target_headquarter | pointE | 1 |
    And cab should have 2 rows
    And team member should have 5 rows
    Then when fetch for cab request is made should have total cost of 22 and below details:
      | cabId | teamMemberIds | route                            | routeCost |
      | 1     | 1,0           | target_headquarter,pointD,pointA | 2         |
      | 2     | 3,2,5         | target_headquarter,pointB,pointC | 20        |




