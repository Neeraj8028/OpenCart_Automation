package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups= {"Regression","Master"})
	public void Register_Verification() {
		
		logger.info("******* Starting TC001_AccountRegistrationTest *******");
//		-------------------- Home page ---------------------
		try {
			
			HomePage home = new HomePage(driver);
			logger.info("******* Clicked on MyAccount Link*******");
			home.ClickMyAccount();
			logger.info("******* Clicked on Register Link*******");
			home.ClickRegister();
			
			
//			------------ Registration page--------
		
			AccountRegistrationPage register = new AccountRegistrationPage(driver);
			
			logger.info("******* Provinding Customer's Details*******");
			register.FirstName(randomString());
			register.LastName(randomString());
			register.EMail(randomString()+"@gamil.com");
			register.Phone_No(randomNumber());
			
			String password = randomPassword();
			register.Password(password);
			register.Confirm_Pwd(password);
			register.P_Policy();
			register.Continue_button();
			
			logger.info("******* Validating Register Account Confirmation Message ********");

			String confirmMSG = register.getConfirmationMsg();
			Assert.assertEquals(confirmMSG, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test Failed...");
			logger.debug("Debug Logs...");
			Assert.fail();
		}
		logger.info("******* Finished TC001_AccountRegistrationTest ********");

	}
	
	
	
}
