package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

//	Constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
//	Locators
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgConfirmation;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement logout;

	
	
//	Action methods
	public boolean isMyAccountPageExist() {
		try {
			return msgConfirmation.isDisplayed();
		}
		catch(Exception e){
			return false;
		}
	}
	
	public void Account_Logout() {
		logout.click();
	}
}
