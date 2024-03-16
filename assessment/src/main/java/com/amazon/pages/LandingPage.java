package com.amazon.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.utils.FrameworkContext;

public class LandingPage extends CommonPage{
	
	private WebDriver browser;
	private String application_URL;
	

	@FindBy(id="twotabsearchtextbox")
	private WebElement wbSearchTxtBx;
	
	@FindBy(id="nav-search-submit-button")
	private WebElement wbSearchBtn;
	
	@FindBy(xpath="//a[contains(@href,'/sspa/click?ie=UTF8&spc=')]//img")
	private WebElement wbSearchResultImg;
	
	
	

	public LandingPage(FrameworkContext context) {
		super(context);
		this.browser = context.getBrowser();
		this.application_URL = context.getApplication_URL();
		
	}
	
	private static Logger log = LogManager.getLogger(LandingPage.class);
	

	public void NavigateToLandingPage()
	{
		browser.get(application_URL);
		waitForAllEmentsVisible(wbSearchBtn);	
	}
	
	public List<WebElement> searchProductGetResults(String productName)
	{
		enterText(wbSearchBtn, productName);
		
		return waitForAllEmentsVisible(wbSearchResultImg);
	}
	
	public void selectProduct(WebElement product)
	{
		waitForElementClickable(product).click();
	}
	
}
