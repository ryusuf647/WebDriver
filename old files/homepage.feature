@tag
Feature: Homepage Test
  Tests being able to navigate to the homepage and login link functionality

  @tag1
  Scenario: Home page navigation
    Given My chrome driver is initialized
    When I navigate to "https://www.seocreed.xyz/clients/index.php"
    Then I am able to see the header text when the page is loaded
    And The login link is displayed