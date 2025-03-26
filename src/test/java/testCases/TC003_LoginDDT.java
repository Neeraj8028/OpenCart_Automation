package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class , groups="DataDriven") // here we are spicified the name of the data provider and class of it 
	
	public void Verify_Login(String email , String pwd , String exp_res) {
		
		logger.info("************************ TC003_LoginDDT Test Case Started**************");
		
		try {
//		HomePage ... Click MyAccount and Login btn
		HomePage hp = new HomePage(driver);
		
		hp.ClickMyAccount();
		hp.ClickLogin();
		
//		LoginPage 
		LoginPage lp = new LoginPage(driver);
		
		lp.LoginEmail(email);
		lp.Loginpassword(pwd);
		lp.LoginButton();
		
//		MyAccount Page 
		MyAccountPage Account = new MyAccountPage(driver);
		boolean TargetPage = Account.isMyAccountPageExist();
		
		/*
		 * Valid Data ----> Login Success ---> Test Pass
		 * Valid Data ----> Login Fail ----> Test Fail
		 * 
		 * Invalid Data ---> Login Pass---> Test Fail
		 * Invalid Data ---> Login Fail ---> Test Pass
		 * 
		 * */
		
		if(exp_res.equalsIgnoreCase("Valid")) {
			if(TargetPage==true) {
				Account.Account_Logout();
				Assert.assertTrue(true); // test passed
			}else {
				Assert.assertTrue(false); // test failed
			}
		}
		if(exp_res.equalsIgnoreCase("InValid")) {
			if(TargetPage==true) {
				Account.Account_Logout();
				Assert.assertTrue(false); // test failed
			}else {
				Assert.assertTrue(true); // test passed
			}
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		
		
		logger.info("************************ TC003_LoginDDT Test Case Finished **************");

	}
}
