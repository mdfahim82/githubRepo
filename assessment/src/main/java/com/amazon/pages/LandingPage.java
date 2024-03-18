package com.amazon.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.utils.FrameworkContext;

public class LandingPage extends CommonPage{
	
	private WebDriver _browser;
	private String _application_URL;
	

	@FindBy(id="twotabsearchtextbox")
	private WebElement wbSearchTxtBx;
	
	@FindBy(id="nav-search-submit-button")
	private WebElement wbSearchBtn;
	
	@FindBy(xpath="//a[contains(@href,'/sspa/click?')]//img")
	private List<WebElement> wbSearchResultImg;
	
	@FindBy(xpath="//button[text()='Continue shopping']")
	private WebElement wbContinueShoppingBtn;
	

	public LandingPage(FrameworkContext context) {
		super(context);
		this._browser = context.getBrowser();
		this._application_URL = context.getApplication_URL();
		
	}
	
	private Logger _log = LogManager.getLogger(LandingPage.class);
	

	public boolean NavigateToLandingPage()
	{
		_browser.navigate().to(_application_URL);
		
		try {
			waitForElementVisibility(wbSearchTxtBx);
		}catch(WebDriverException we)
		{
			if(wbContinueShoppingBtn.isDisplayed())
			{
				_log.info("Capcha cannot be automated. Please stop and retry execution");
				return false;
			}
		}
		return true;
			
	}
	
	public List<WebElement> searchProductGetResults(String productName)
	{
		_log.info("Search for Product: {}",productName);
		enterText(wbSearchTxtBx, productName);
		click(wbSearchBtn);
		
		waitForElementClickable(wbSearchResultImg.get(0));
		
		return wbSearchResultImg;
	}
	
	public void selectProduct(WebElement product)
	{
		waitForElementClickable(product).click();
	}
	
}
