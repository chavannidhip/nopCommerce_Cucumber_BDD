package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer;

public class steps extends BaseClass{

	@Before
	public void setup() throws IOException {
		// Logger configuration
		logger= Logger.getLogger("nopCommerce Cucumber Selenium Framework"); // Added logger
		PropertyConfigurator.configure("log4j.properties"); // configure property file

		// Config properties
		config=new Properties();
		FileInputStream configProp = new  FileInputStream("config.properties");
		config.load(configProp);
	
		// Setup driver and choose browser		
		String br=config.getProperty("browser");
		if(br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			logger.info("************Launching Chrome browser ********");
		}
		else if(br.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			logger.info("************Launching Edge browser ********");
		}
	}
	
	
	// steps for Login Feature
	@Given("User Launch Chrome Browser")
	public void user_launch_chrome_browser() {
		lp= new LoginPage(driver);
	}

	@When("User opens URl {string}")
	public void user_opens_u_rl(String url) {
		driver.get(url);
		logger.info("************Opening URL ********");
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("************Providing login details ********");
		lp.SetUserEmail(email);
		lp.SetPassword(password);
	}

	@When("click on Login")
	public void click_on_login() {
		lp.clickSubmit();
		logger.info("************Started logging process ********");
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
	  
		if(driver.getPageSource().contains("Login was unsuccessful"))
		{
			logger.info("************Login Failed ********");
			driver.close();
			Assert.assertTrue(false);
		}
		else
		{
			logger.info("************Login Passed ********");
			Assert.assertEquals(driver.getTitle(), title);
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
	    lp.clickLogout();
		logger.info("************Clicked on Logout********");
	    Thread.sleep(1000);
	}

	@Then("close browser")
	public void close_browser() {
	    driver.quit();
	}

	// Add steps for customers feature - 8 steps
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		cp= new CustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration",cp.getPageTitle());
	}
	
	@When("User clicks on customer Menu")
	public void user_clicks_on_customer_menu() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("************Clicked on Customer Menu ********");
		cp.clickonCustomersMenu();
	}

	@When("click on customers menu item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		cp.clickonCustomerItem();
	}
	
	@When("click on add new button")
	public void click_on_add_new_button() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("************Clicking on Add new button ********");
		cp.clickbtnAddNew();
	}
	
	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		logger.info("************Add new Customer page  ********");
		Assert.assertEquals("Add a new customer / nopCommerce administration",cp.getPageTitle());
	}
	
	@When("User enters customer info")
	public void user_enters_customer_info() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("************ Adding Customer details ********");
		String email=randomString()+"@gmail.com";
		cp.enterEmail(email);
		cp.enterPassword("1234");
		cp.enterFName("ABC");
		cp.enterLName("PQR");
		cp.selectGender("Female");
		cp.enterDOB("12/10/2010");
	}

	@When("click on save button")
	public void click_on_save_button() {
		cp.clickSave();
		logger.info("************Clicking on save button ********");

	}
	
	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String message) {
		if(driver.getPageSource().contains(message)) {
			Assert.assertTrue(true);
			logger.info("************New customer added ********");
		}
		else {
			Assert.assertFalse(true);
			logger.info("**********Adding customer failed ********");
		}
	}
	
	// Add new steps for search customer feature using Email id ............
	
	@When("Enter Customer Email {string}")
	public void enter_customer_email(String emailId) {
		sc= new SearchCustomer(driver);
		sc.setEmail(emailId);
	}

	@When("Click on Search button")
	public void click_on_search_button() throws InterruptedException {
		logger.info("************Searching for customer using email********");

		sc.clickOnSearch();
		Thread.sleep(2000);
	}

	@Then("User should found Email in the search table {string}")
	public void user_should_found_email_in_the_search_table(String email) {

		Assert.assertEquals(sc.searchCustomerByEmail(email), true);
	}

	
	
	// Add new step for search Customer feature using first name and last name

	@When("Enter Customer FirstName as {string}")
	public void enter_customer_first_name_as(String fName) {
		sc= new SearchCustomer(driver);
		sc.setFirstName(fName);
	}

	@When("Enter Last name as {string}")
	public void enter_last_name_as(String lName) {
		sc.setLastName(lName);
	}

	@Then("User should found name in the search table {string}")
	public void user_should_found_name_in_the_search_table(String name) {
		logger.info("************Searching for customer using name********");

		Assert.assertEquals(sc.searchCustomerByName(name), true);
	}



}
