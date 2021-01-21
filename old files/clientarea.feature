@tag
Feature: Client area Test
  Confirms client area is displayed and 

  @tag4
  Scenario: Client area - Welcome message
    And I enter "ryusuf647@hotmail.com" and "21Duncan21!" on the login page and click login
    Then I am able to log in successfully and see the welcome message with my name
    
	@tag5
	Scenario: Client area nodes - no. of nodes, labels, stats
		And I am able to confirm the following are correct: no. of nodes, labels, and stats