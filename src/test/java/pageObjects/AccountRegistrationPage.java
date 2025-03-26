package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//---------------------------------- Page 2 --------------------------------------
public class AccountRegistrationPage extends BasePage{

//	constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
//	locator
	@FindBy(xpath="//input[@id='input-firstname']") WebElement FirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement LastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement E_Mail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement Telephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement Password;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement CnfPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement PrivacyPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement ContinueBTN;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;

//	action methods
	
	public void FirstName(String fname) {
		FirstName.sendKeys(fname);
	}
	public void LastName(String lname) {
		LastName.sendKeys(lname);
	}
	public void EMail(String email) {
		E_Mail.sendKeys(email);
	}
	public void Phone_No(String tel) {
		Telephone.sendKeys(tel);
	}
	public void Password(String pwd) {
		Password.sendKeys(pwd);
	}
	public void Confirm_Pwd(String cnfpwd) {
		CnfPassword.sendKeys(cnfpwd);
	}
	public void P_Policy() {
		PrivacyPolicy.click();
	}
	public void Continue_button() {
		ContinueBTN.click();
	}
	//s0l2

	//btnContinuciETIIRIOR

	//s013

	//Actions act=new Actions(driver);

	//act.moveToElement (btnContinue).click().perform();

	//sola

	//3avascriptExecutor js=(JavascriptExecutor)driver;
	//3s.executescript(“arguments[@].click();", btnContinue);

	//s0l 5

	//btnContinue.sendKeys (Keys.RETURN) ;

	//s016

	//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	
	public String getConfirmationMsg() {
		try {
			return  msgConfirmation.getText();
		}catch(Exception e){
			return (e.getMessage());
		}
		
		
	}
}
