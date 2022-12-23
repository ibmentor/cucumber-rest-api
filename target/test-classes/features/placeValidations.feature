Feature: Validating place API
@AddPlace @Regression
  Scenario Outline: :
    Given Add Place Payload with "<name>","<address>","<languages>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API is call is success with status code 200
    Then "status" in response body is "OK"
    Then "scope" in response body is "APP"
    Then Verify place id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name           | address | languages |
      | Akshay Gaikwad | N2      | marathi   |

  #  | Rahul Gaikwad  | B6 cidco | English   |
  
  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API is call is success with status code 200
    Then "status" in response body is "OK"
