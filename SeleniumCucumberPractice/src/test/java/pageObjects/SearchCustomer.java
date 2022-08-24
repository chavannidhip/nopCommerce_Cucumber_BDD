package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomer {
	public WebDriver driver;
	WaitHelper waithelper;
	
	public SearchCustomer(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
		waithelper= new WaitHelper(driver); 
	}
	
	@FindBy(how= How.ID, using ="SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(how= How.ID, using ="SearchFirstName")
	@CacheLookup
	WebElement txtFName;
	
	@FindBy(how= How.ID, using ="SearchLastName")
	@CacheLookup
	WebElement txtLName;

	@FindBy(how= How.ID, using ="search-customers")
	@CacheLookup
	WebElement btnSearch;

	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;
	
	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']/thead/tr/th")
	@CacheLookup
	List<WebElement> tableColumns;
		
	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']/tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	public void setEmail(String email) {
		waithelper.waitForElement(txtEmail, Duration.ofSeconds(10));
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setFirstName(String fName) {
		waithelper.waitForElement(txtFName, Duration.ofSeconds(10));
		txtFName.clear();
		txtFName.sendKeys(fName);
	}

	public void setLastName(String lName) {
		waithelper.waitForElement(txtLName, Duration.ofSeconds(10));
		txtLName.clear();
		txtLName.sendKeys(lName);
	}

	public void clickOnSearch() {
		btnSearch.click();
	}
	
	public int getNoOfRows() {
		return tableRows.size();
	}
	public int getNoOfColumns() {
		return tableColumns.size();
	}
	
	public boolean searchCustomerByEmail(String emailId) {
		boolean status = false;

		for (int i=1;i<=getNoOfRows(); i++)
		{
			String rowText= driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+ i+ "]/td[2]")).getText();
			if(rowText.equals(emailId))
				{
					status=true;
					break;
				}
		}
		return status;
	}
	
	public boolean searchCustomerByName(String name) {
		boolean status = false;
		for (int i=1;i<=getNoOfRows(); i++)
		{
			String rowText= driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+ i+ "]/td[3]")).getText();
			if(rowText.equals(name))
				{
					status=true;
					break;
				}
		}
		return status;
		
	}
	
}
