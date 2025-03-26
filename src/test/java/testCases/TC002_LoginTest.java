package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void Verify_Login() throws IOException {
		
		logger.info("******* Starting TC002_LoginTest *******");
		
		try {
//		---------------For login first we need to go Myaccount -> Login----------------
		
//		HomePage
		HomePage hp = new HomePage(driver);
		
		hp.ClickMyAccount();
		hp.ClickLogin();
		
//		LoginPage
		LoginPage lp = new LoginPage(driver);
		
		lp.LoginEmail(p.getProperty("Email"));
		lp.Loginpassword(p.getProperty("Password"));
		lp.LoginButton();
		
//		MyAccount Page 
		MyAccountPage Account = new MyAccountPage(driver);
		boolean status = Account.isMyAccountPageExist();
		Assert.assertEquals(status, true ,"Login Failed");
//		Account.Account_Logout();
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("******* Finished TC002_LoginTest *******");

	}
}
