@ui
Feature: Locate and click elements
  As a user of ultimateqa.com
  I want to make sure I can locate and click various elements successfully
  so the framework can be tested successfully.

Scenario: Access ultimateqa.com and click a button using ID
Given access to ultimateqa.com
When the user locates a button by ID
Then it can be clicked successfully
    
Scenario: Access ultimateqa.com and click a button using classname
Given access to ultimateqa.com
When the user locates a button by classname
Then it can be clicked successfully

Scenario: Access ultimateqa.com and click a button using name
Given access to ultimateqa.com
When the user locates a button by name
Then it can be clicked successfully