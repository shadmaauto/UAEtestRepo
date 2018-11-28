package com.talabat.tests;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.talabat.base.TestBase;
import com.talabat.pages.AllRestaurantsPage;
import com.talabat.pages.HomePage;
import com.talabat.pages.LoginPage;
import com.talabat.pages.SearchResultsPage;
import com.talabat.pages.SpecificRestaurantPage;

/*This class covers all test cases of All Restaurants Page of Talabat Website.*/
public class AllRestaurantsPageTest extends TestBase{

	HomePage homePage;
	AllRestaurantsPage allRestPg;
	SpecificRestaurantPage spRestPg;
	String desiredRowNumber;
	//String sheetName="AllRestaurantsPg";

	public AllRestaurantsPageTest() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initializaton();
		homePage=new HomePage();
		allRestPg=homePage.clickAllRestaurantsLink();
	}
	
		
	//@Test
	public void verifyRestaurantLinkByNameTest() {
		allRestPg.verifyRestaurantLinkByName("Baba Jan");
	}
	
	@Test
	public void verifySearchRestaurantByNameTest() throws IOException {
		List<Integer> liData=getAllMatchedRowNumberList("TC_001");
		//System.out.println("no. of matching rows are:"+liData.size());
		for(int i=0;i<liData.size();i++){
			//System.out.println("Run no.:"+i);
			getTestCaseDatapoi(liData.get(i));
		//System.out.println("value got from datamap is ::  "+DataMap.get("RestaurantNameTextBox"));
		allRestPg.searchRestaurantByName(DataMap.get("RestaurantNameTextBox"));
		//allRestPg.searchRestaurantByName("Donor Deli");
		//Assert.assertEquals(spRestPg.DRIVER.getTitle(),"Baba Jan delivery in Dubai, Abu Dhabi and many other cities | Baba Jan menu | Talabat","Specific restaurant page title is wrong!!");
	}
	}

	@AfterMethod
	public void tearDown() {
		DRIVER.quit();
	}
	
	
}
