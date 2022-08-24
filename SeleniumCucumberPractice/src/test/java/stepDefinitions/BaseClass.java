package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import pageObjects.CustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer;

public class BaseClass {

	public WebDriver driver; 
	public LoginPage lp;
	public CustomerPage cp;
	public SearchCustomer sc;
	public static Logger logger;
	public Properties config;
	
	// Random string for email id creation
	public static String randomString() {
		String newRandomString = RandomStringUtils.randomAlphabetic(5);
		return newRandomString;
	}
	
	
}
