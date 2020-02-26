# new feature
# Tags: optional

Feature: Users API

  @debug
  Scenario: Successful User Creation
    Given the following body is prepared:
      | name    | salary | age |
      | Gosheto | 123    | 23  |
    When the api client does a POST to "/api/v1/create"
    Then a response with status code 200 is returned
    And the response payload on "$" contains:
      | status  |
      | success |
    And the response payload on "data" contains:
      | name    | salary | age | id      |
      | Gosheto | 123    | 23  | ^[\\d]+ |