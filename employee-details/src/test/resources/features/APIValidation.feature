#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
Feature: Validation of get and post method

  @GetUserDetails
  Scenario Outline: Send a valid Request to get user details
    Given I send a request to the URL to get user details
    Then the response will return userName "<userName>" and password "<password>" and category "<category>" and name "<name>" and emailAddress "<emailAddress>" and phoneNumber "<phoneNumber>"

    Examples: 
      | userName | password      | category  | name      | emailAddress                 | phoneNumber |
      | zxsdr   | Sep@2022AV | Sr.Consultant | zxsdrrt234 |zxsdrrt234.aawesdf@gmail.com |7644221990|
  
  Scenario: Add Employee record 
   Given I Set POST employee service api endpoint
   When I Set request HEADER
   And Send a POST HTTP request 
   Then I receive valid Response