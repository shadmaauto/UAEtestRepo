package com.talabat.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.talabat.base.TestBase;
import com.talabat.pages.HomePage;
import com.talabat.pages.LoginPage;

/*This class covers the test cases of Login Page of Talabat website*/

public class LoginPageTest extends TestBase{

	
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() {
		//to call parent class constructor
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initializaton();
	    loginPage=new LoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		//DRIVER.quit();
	}
	
	//@Test(priority=1)
	public void loginPageTitleTest() {
		String title=loginPage.validateLoginPageTitle();
		Assert.assertEquals(title,"Order food online from delivery restaurants in UAE | Talabat");
	}
	
	//@Test(priority=2)
	public void loginPageLogoTest() {
		Boolean flag=loginPage.validateTalabatLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest() {
		homePage=loginPage.login(prop.getProperty("UserName"),prop.getProperty("Password"));
	    String myAcctLabelText=homePage.verifyMyAccountLabel();
	    Assert.assertEquals(myAcctLabelText, "MY ACCOUNT","label not matched");
	}
	
	
	
}
