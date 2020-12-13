Feature: Registration Form 
 
  Scenario Outline: sign up Registration Form  
  to Complete the Sign up Form
    Given I go to Sign up Registraion Form  
    When I type for first name "<firstName>"
    And I type for last name "<lastName>"
    And I type for email "<email>"
    And I type for user name "<userName>"
    And I type for Pass "<password>"
    And I type for confirm Password "<confirmPass>"
    And I click on Register search
    #Then Confirmation message should become "<confirmMessage>"  
    And Click on menu drop down
    Then Email id should verified as "<email>"
    And Click on compose option.
    And I type for send to "ingtrend12"
    And I type for Subject "<subject>"
    And I type for Message "<subject>"
		And click on send message
		Then acknowledgement message should "<ackMessage>"
		
 Examples: 
      | firstName | lastName   	| email                     |userName     |password    |confirmPass|subject   |ackMessage|
      | Aishwarya | AV			| ingtrend12@gmail.com 		|ingtrend12	  |Zaqtest@123 |Zaqtest@123|Hi Testing|The message has been sent to Aishwarya AV (ingtrend12)|
     