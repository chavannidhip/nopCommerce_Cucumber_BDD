package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

		public WebDriver driver;
		
		public LoginPage(WebDriver driver){
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}

		@FindBy(id="Email") 
		@CacheLookup 
		WebElement txtUserEmail;
		
		@FindBy(id="Password") 
		@CacheLookup 
		WebElement txtPassword;
		
		@FindBy(xpath="//button[text()='Log in']") 
		@CacheLookup 
		WebElement btnLogin;
		
		@FindBy(linkText="Logout")
		@CacheLookup
		WebElement btnLogout;
		
		
		public void SetUserEmail(String email)
		{
			txtUserEmail.clear();
			txtUserEmail.sendKeys(email);
		}
		
		public void SetPassword(String password) 
		{
			txtPassword.clear();
			txtPassword.sendKeys(password);
		}
		
		public void clickSubmit()
		{
			btnLogin.submit();
		}
		
		public void clickLogout()
		{
			btnLogout.click();
		}

	}
