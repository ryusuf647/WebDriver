Feature: Cucumber automation framework  

  Scenario: Home page navigation
    Given My chrome driver is initialized
    When I navigate to "https://www.seocreed.xyz/clients/index.php"
    Then I am able to see the header text when the page is loaded
    And The login link is displayed

  Scenario: Login page displayed
    When I click the login link
    Then the sign in page is displayed with the header "Login This page is restricted"

  Scenario Outline: Validating login
    When I enter <username> and <password> for the fields and click log in
    Then I am able to confirm that logout button is displayed if login is successful

    Examples: 
      | username  						| password		| status	|	result						|
      | ryusuf647@hotmail.com	| 21Duncan21!	| pass		|	logout displayed	|
      | test1@gmail.com				| testing 		| fail		|	error displayed		|
      
  Scenario: Client area - Welcome message
    When I enter "ryusuf647@hotmail.com" and "21Duncan21!" on the login page and click login
    Then I am able to log in successfully and see the welcome message with my name
    
	Scenario: Client area nodes - no. of nodes, labels, stats
		And I am able to confirm the following are correct: no. of nodes, labels, and stats
		
	Scenario: Client area - validate order new services menu item
		When I click Services on the menu bar
		Then I am able to see the Order New Services menu item
		
	Scenario: Services - Header and add services test
		When I click on Order New Services
		Then I am able to see the services page with the correct header
		And I am able to add services
		
	Scenario: Cart - Header, items, and sum test
		And I am able to confirm the header text is correct on the cart page
		And I am able to confirm that the services I added are correct
		And The corresponding prices are correct
		And The order total is correct  