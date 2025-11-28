Feature: login into Application

  Scenario: Login page
    Given Launch browser
    When Enter the application URL "URL"
    And Enter credentials in respective fields
    Then Click on login Button
    And Navigate to "Practice Management" module
