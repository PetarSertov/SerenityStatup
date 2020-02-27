# new feature
# Tags: optional

Feature: Users API

  @BUG:123
  Scenario: Successful GET an employee
    When the api client does a GET request to "/api/v1/employee/{employee_id}":
      | employee_id |
      | 2           |
    Then a response with status code 200 is returned
    And the response payload on "$" contains:
      | status  |
      | success |
    And the response payload on "data" contains:
      | employee_name   | employee_salary | employee_age | profile_image | id |
      | Garrett Winters | 170750          | 63           |               | 2  |

  Scenario: Successful GET all employees
    When the api client does a GET request to "/api/v1/employees"
    Then a response with status code 200 is returned
    And the response payload on "$" contains:
      | status  |
      | success |
    And the response payload on "data" is an array

    @debug
  Scenario: Successful User Creation
    Given the following body is prepared:
      | name            | salary | age |
      | Milko Kavaljiev | 5000   | 43  |
    When the api client does a POST request to "/api/v1/create"
    Then a response with status code 200 is returned
    And the response payload matches json schema "user_o"
    And the response payload on "$" contains:
      | status  |
      | success |
    And the response payload on "data" contains:
      | name            | salary | age | id      |
      | Milko Kavaljiev | 5000   | 43  | ^[\\d]+ |
