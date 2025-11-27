Feature: Client Creation

  Background: login
    Given Launch browser
    When Enter the application URL "https://account.beta.capium.co.uk/Account/Login"
    #When Enter the application URL "https://app.capium.com/sign-in.aspx?"
    And Enter "userName" and "passWord" in Respective fields
    Then Click on login Button
    And Navigate to "SAMTD" module

  Scenario: Client Creation in SAMTD
    Given click on Manage tab
    When click on Add client button
    And Enter mandatory fields
