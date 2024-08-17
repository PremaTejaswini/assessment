package prematest.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import prematest.config.basetest;
import prematest.pageobjects.LoginScreen;
import prematest.pageobjects.SuccessScreen;

public class TestCase extends basetest {
	
	@Test
	public void loginandLogout(){
		
		landingPage.navigation();
		LoginScreen login = new LoginScreen(driver);
		SuccessScreen Home = login.Login();
		String actualMessage = Home.extractsuccessMessage();
		Assert.assertEquals(actualMessage, "You logged into a secure area");
		Home.logOut();


		
		
	}

}
