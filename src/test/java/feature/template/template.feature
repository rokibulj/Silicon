Feature: Feature Template

  #Scenario: Sample scenario
  #Given My api base URL is "baseUrl"
  #And Port number is "port"
  #When My request method is "method"
  #And I set header "key" as "value"
  #And I set query parameter "param" as "value"
  #And I set path parameter "param" as "value"
  #And I set request body from "fileName"
  #And I make a request to "endpoint"
  #Then I expect status code as "statusCode"
  #And I want to print response body
  #And I expect "node" equals "value" in response body
  #And I expect "node" contains "value" in response body
  
  
  Scenario: positive test
    Given My api base URL is "http://token.qa.elitecareer.net"
#	    And Port number is "port"
	    When My request method is "POST"
	    And I set header "fusion-api-key" as "fusionqateam"
#	    And I set query parameter "param" as "value"
#	    And I set path parameter "param" as "value"
	    And I set request body from "EmployerValidCredential.jason"
	    And I make a request to "api/v1/user"
    Then I expect status code as "201"
	    And I want to print response body
#	    And I expect "node" equals "value" in response body
#	    And I expect "node" contains "value" in response body
