package com.talabat.pages;

import org.openqa.selenium.support.PageFactory;

import com.talabat.base.TestBase;

public class OffersPage extends TestBase{

	public OffersPage() {
		PageFactory.initElements(DRIVER, this);
	}
	
	public String verifyOffersPgTitle() {
		return DRIVER.getTitle();
	}
	
}
