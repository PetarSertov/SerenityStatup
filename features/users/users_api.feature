# new feature
# Tags: optional

Feature: Users API

  Scenario: Get All Users
    When Ivan does GET request to "/employees"
    Then request is successful with code 200
    And all existing users are returned