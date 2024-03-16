package com.amazon.test;

import org.testng.annotations.Test;

import com.amazon.pages.LandingPage;

public class AddToCartTest extends TestMain{


	

	@Test
	public void AddFirstProductInCart()
	{
		LandingPage lp = new LandingPage(frameworkContext);
		lp.NavigateToLandingPage();
		lp.searchProductGetResults(statusFail);// provide test data
	}
	
	
	
	
	
	
	

}
