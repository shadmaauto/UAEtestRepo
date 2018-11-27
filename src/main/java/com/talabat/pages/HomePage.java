package com.talabat.pages;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.talabat.base.TestBase;

public class HomePage extends TestBase {

	
	//@FindBy(xpath="//a[@class='nav-link accountPopup']//[contains(text,'MY ACCOUNT')]")
	@FindBy(xpath="//li[@class='nav-list-li myaccountLink-li text-uppercase']//a[@class='nav-link accountPopup']")
	WebElement myAcctLabel;
	
	//@FindBy(xpath="//li[@class='nav-list-li inner-li']//a[contains(text,'OFFERS')]")
	@FindBy(xpath="//li[@class='nav-list-li inner-li']//a[@href='/uae/offers']")
	WebElement offersLink;
	
	@FindBy(xpath="//li[@class='nav-list-li inner-li']//a[@href='/uae/top-selling']")
	WebElement mostSellingLink;
	
	@FindBy(xpath="//li[@class='nav-list-li inner-li']//a[@href='/uae/restaurants']")
	//@FindBy(xpath="//button[contains(text,'Create an account')]")
	//@FindBy(xpath="//a[contains(text,'ALL RESTAURANTS')]")
	WebElement clickAllRestaurantsLink;
	
	@FindBy(xpath="//span[@ng-click='clearCity()']//i[@class='fa fa-angle-down']")
	WebElement selectCityDropdownArrow;
	
	@FindBy(xpath="//span[@ng-click='clearArea()']//i[@class='fa fa-angle-down']")
	WebElement selectAreaDropdownArrow;
	
	@FindBy(xpath="//span[@ng-click='clearCuisine()']//i[@class='fa fa-angle-down']")
	WebElement chooseCuisineDropdownArrow;
	
	@FindBy(xpath="//a[@title='Ras Al Khaima']")
	WebElement cityOption;
	
	
	@FindBy(xpath="//input[@placeholder='Select Your City']")
	WebElement selectCityField;
	
	@FindBy(xpath="//input[@placeholder='Choose Cuisine']")
	WebElement areaOptions;
	
	@FindBy(xpath="//input[@placeholder='Choose Cuisine']")
	WebElement chooseCuisineOption;
	
	
	@FindBy(xpath="//Button[contains(text,'Find Restaurants')]")
	WebElement findRestaurantsBT;
	
	public HomePage() {
		PageFactory.initElements(DRIVER, this);
	}
	
	public String verifyHomepageTitle() {
		 return DRIVER.getTitle();
	}
	
	public String verifyMyAccountLabel() {
		//System.out.println(myAcctLabel.getText()); 
		//myAcctLabel.click();
		return myAcctLabel.getText();
		}
	
	public OffersPage clickOffersLink() {
		offersLink.click();
		return new OffersPage();
	}
	
	public MostSellingPage clickMostSellinkLink() {
		mostSellingLink.click();
		return new MostSellingPage();
	}
	
	public AllRestaurantsPage clickAllRestaurantsLink() {
		//Actions actions = new Actions(DRIVER);

		//actions.moveToElement(clickAllRestaurantsLink).click().perform();
		clickAllRestaurantsLink.click();
		//System.out.println("done");
		return new AllRestaurantsPage();
	}
	
	/*public SearchResultsPage findRestaurants(String city,String area,String cuisine) throws IOException {
		
		selectByText(selectCity, city);
		selectByText(selectArea, area);
		selectByText(chooseCuisine, cuisine);
		findRestaurantsBT.click();
		return new SearchResultsPage();
	}*/
	
	public SearchResultsPage findRestaurants(String city,String area,String cuisine) throws IOException {
		
		//Alert alert=DRIVER.switchTo().alert();//yaha
		//alert.dismiss();
		selectCityField.click();
		cityOption.click();
		areaOptions.click();
		chooseCuisineDropdownArrow.click();
		chooseCuisineOption.click();
		findRestaurantsBT.click();
		return new SearchResultsPage();
	}
}
