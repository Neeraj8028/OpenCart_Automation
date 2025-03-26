package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage  {
	
//	constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
//	locators
	
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	@FindBy(linkText="Login") WebElement Loginlink;
	
	
//	action methods
	
	public void ClickMyAccount() {
		
		lnkMyAccount.click();
	}
	public void ClickRegister() {
		lnkRegister.click();
	}
	public void ClickLogin() {
		Loginlink.click();
	}
	
}
