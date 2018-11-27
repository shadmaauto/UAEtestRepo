package com.talabat.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.talabat.base.TestBase;
import com.talabat.pages.AllRestaurantsPage;
import com.talabat.pages.HomePage;
import com.talabat.pages.LoginPage;
import com.talabat.pages.MostSellingPage;
import com.talabat.pages.OffersPage;
import com.talabat.pages.SearchResultsPage;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	SearchResultsPage srPage;
	OffersPage offersPg;
	MostSellingPage mostSellingPg;
	AllRestaurantsPage allRestPg;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initializaton();
		loginPage=new LoginPage();
		srPage=new SearchResultsPage();
		homePage=new HomePage();
		//homePage=loginPage.login(prop.getProperty("UserName"),prop.getProperty("Password"));
	}
	
	
	//@Test(priority=1)
	public void verifyHomePAgeTitleTest() {
		String hmPgTitle=homePage.verifyHomepageTitle();
		Assert.assertEquals(hmPgTitle, "Order food online from delivery restaurants in UAE | Talabat","HomePage title not matched");
	}
	
	//@Test(priority=2)
	public void clickMyAccountLabelTest() {  
		String myAcctLabelText=homePage.verifyMyAccountLabel();
	    Assert.assertEquals(myAcctLabelText, "MY ACCOUNT","MY ACCOUNT label not matched");
	}
	
	//@Test(priority=2)
	public void clickOffersLinkTest() {  
		offersPg=homePage.clickOffersLink();
	    Assert.assertEquals(offersPg.verifyOffersPgTitle(), "Restaurants offers near me in UAE | Talabat","Offers page label not matched");
	}
	
	//@Test(priority=2)
	public void clickMostSellingLinkTest() {  
		mostSellingPg=homePage.clickMostSellinkLink();
	    Assert.assertEquals(offersPg.verifyOffersPgTitle(), "Restaurants offers near me in UAE | Talabat","Offers page label not matched");
	}
	
	@Test(priority=2)
	public void clickAllRestaurants() {  
		allRestPg=homePage.clickAllRestaurantsLink();
		System.out.println("done2");
	   // Assert.assertEquals(offersPg.verifyOffersPgTitle(), "Restaurants offers near me in UAE | Talabat","Offers page label not matched");
	}
	
	
	//@Test(priority=2)
	public void findRestaurantsTest() throws IOException {
	     System.out.println(prop.getProperty("city"));
	     System.out.println(prop.getProperty("area"));
	     System.out.println(prop.getProperty("cuisine"));
		srPage=	homePage.findRestaurants(prop.getProperty("city"), prop.getProperty("area"), prop.getProperty("cuisine"));
		System.out.println("search done");
	}
	
	@AfterMethod
	public void tearDown() {
		DRIVER.quit();
	}
	
	
	
}
