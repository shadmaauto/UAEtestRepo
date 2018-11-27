package com.talabat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.talabat.base.TestBase;

public class AllRestaurantsPage extends TestBase{

	public AllRestaurantsPage() {
		PageFactory.initElements(DRIVER, this);
	}
	
	@FindBy(id="search-rest")
	@CacheLookup
	WebElement weSearchTextBox;
	
	@FindBy(xpath="//i[@class='fa fa-search']")
	WebElement weSearchIcon;
	
	@FindBy(xpath="//i[@class='fa fa-close']")
	WebElement weCloseIcon;
	
	@FindBy(xpath="//b[contains(text,'Baba Jan')]")
	WebElement weRestaurantName;
	
	
	
	
	public void verifyRestaurantLinkByName(String restName) {
		WebElement weRest=DRIVER.findElement(By.xpath("//b[contains(text,restName)]"));
		weRest.click();
		System.out.println(weRest);
		System.out.println("1");
	}
	
	public void searchRestaurantByName(String restName) {
		weSearchTextBox.clear();
		weSearchTextBox.sendKeys(restName);
		//weSearchIcon.click();
		//weCloseIcon.click();
		//weRestaurantName.click();
		//return new SpecificRestaurantPage();
		
	}
}
