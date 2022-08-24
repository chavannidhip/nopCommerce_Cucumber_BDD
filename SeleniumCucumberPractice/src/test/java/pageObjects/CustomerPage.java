package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPage {

	public WebDriver driver;
	
	public CustomerPage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	By linkCustomerMenu= By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By linkCustomerItem = By.xpath("//ul[@class='nav nav-treeview']//p[contains(text(),'Customers')]");
	By btnAddNew=By.xpath("//a[@class='btn btn-primary']"); 
	By txtEmail=By.id("Email");
	By txtPassword=By.id("Password");
	By txtfirstName=By.id("FirstName");
	By txtlastName=By.id("LastName");
	By radioMGender=By.id("Gender_Male");
	By radioFGender=By.id("Gender_Female");
	By txtDOB=By.id("DateOfBirth");
	By txtCompany=By.id("Company");
	By chkIsTaxExempt=By.id("IsTaxExempt");
	By btnSave=By.xpath("//button[@name=\"save\"]");
	
	public void clickonCustomersMenu() {
		driver.findElement(linkCustomerMenu).click();
	}
	public void clickonCustomerItem() {
		driver.findElement(linkCustomerItem).click();
	}
	public void clickbtnAddNew() {
		driver.findElement(btnAddNew).click();
	}
	public void enterEmail(String email) {
		driver.findElement(txtEmail).sendKeys(email);
	}
	public void enterPassword(String pwd) {
		driver.findElement(txtPassword).sendKeys(pwd);
	}
	public void enterFName(String fname) {
		driver.findElement(txtfirstName).sendKeys(fname);
	}
	public void enterLName(String name) {
		driver.findElement(txtlastName).sendKeys(name);
	}
	public void selectGender(String gender) {
		if(gender.equals("Female"))
			driver.findElement(radioFGender).click();
		else // Male option or default
			driver.findElement(radioMGender).click();
	}
	public void enterDOB(String date) {
		driver.findElement(txtDOB).sendKeys(date);
	}
	public void clickSave() {
		driver.findElement(btnSave).click();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
}
