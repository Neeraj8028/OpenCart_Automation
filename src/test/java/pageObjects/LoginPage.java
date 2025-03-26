package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

//	This is my constructor
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
//	Locator
	
	@FindBy(xpath="//input[@id='input-email']") WebElement Email;
	@FindBy(xpath="//input[@id='input-password']") WebElement password;
	@FindBy(xpath="//input[@value='Login']") WebElement LoginBTN;
	
//	Action Methods
	
	public void LoginEmail(String email) {
		Email.sendKeys(email);;
	}
	public void Loginpassword(String pwd) {
		password.sendKeys(pwd);
	}
	public void LoginButton() {
		LoginBTN.click();
	}
	
}
