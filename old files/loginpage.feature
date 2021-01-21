@tag
Feature: Login page data driven test
  Perform a data driven test for the login page using positive and negative scenarios

  @tag2
  Scenario: Login page displayed
    And I click the login link
    Then the sign in page is displayed with the header "Login This page is restricted"

  @tag3
  Scenario Outline: Validating login
    And I enter <username> and <password> for the fields and click log in
    Then I am able to confirm that logout button is displayed if login is successful

    Examples: 
      | username  						| password		| status	|	result						|
      | ryusuf647@hotmail.com	| 21Duncan21!	| pass		|	logout displayed	|
      | test1@gmail.com				| testing 		| fail		|	error displayed		|
