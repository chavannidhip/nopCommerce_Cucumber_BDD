Feature: Customer related features 

Background: Below are the common steps for these scenarios 
	Given User Launch Chrome Browser 
	When User opens URl "https://admin-demo.nopcommerce.com/login?" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And click on Login 
	Then User can view Dashboard 

@sanity	
Scenario: Add new Customer 
	When User clicks on customer Menu 
	And click on customers menu item 
	And click on add new button 
	Then User can view Add new customer page 
	When User enters customer info 
	And click on save button 
	Then user can view confirmation message "The new customer has been added successfully." 
	And close browser 
	
@regression
Scenario: Search customer using Email Id 
	When User clicks on customer Menu 
	And click on customers menu item 
	And Enter Customer Email "james_pan@nopCommerce.com"
	When Click on Search button 
	Then User should found Email in the search table "james_pan@nopCommerce.com"
	And close browser 

@regression		
Scenario: Search customer using name 
	When User clicks on customer Menu 
	And click on customers menu item 
	And Enter Customer FirstName as "James" 
	And Enter Last name as "Pan" 
	When Click on Search button 
	Then User should found name in the search table "James Pan"
	And close browser 