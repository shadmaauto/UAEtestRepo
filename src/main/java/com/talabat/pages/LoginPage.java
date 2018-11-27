package com.talabat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.talabat.base.TestBase;

public class LoginPage extends TestBase{

	//Page Factory - OR
	@FindBy(xpath="//a[@class='nav-link login-link']")
	WebElement loginLink;
		
	@FindBy(name="username")
	WebElement userName;
	
	@FindBy(name="password")
	WebElement passWord;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement LoginBT;
	
	@FindBy(xpath="//button[contains(text,'Create an account')]")
	WebElement CreateAcctLink;
	
	@FindBy(xpath="//ul[@class='nav-list col-sm-3 hidden-xs p-r-0 main-logo']//img[@src='/images/talabat/logo_new.svg']")
    WebElement talabatLogo;
	
	
	
	//Initializing page objects in constructor
	public LoginPage() {
		PageFactory.initElements(DRIVER, this);
	}
	
	public String validateLoginPageTitle() {
		return DRIVER.getTitle();
	}
    
	public boolean validateTalabatLogo() {
		return talabatLogo.isDisplayed();
	}
	
	public HomePage login(String username,String password) {
		loginLink.click();
		userName.sendKeys(username);
		passWord.sendKeys(password);
		LoginBT.click();
		return new HomePage();
	}
	

}
