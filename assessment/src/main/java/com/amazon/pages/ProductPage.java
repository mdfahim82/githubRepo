package com.amazon.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.utils.FrameworkContext;

public class ProductPage extends CommonPage{
	
	private WebDriver browser;
	

	@FindBy(id="productTitle")
	private WebElement wbProductTitleLbl;
	
	@FindBy(id="nav-search-submit-button")
	private WebElement wbSearchBtn;
	
	@FindBy(xpath="//span[@id='taxInclusiveMessage']/preceding-sibling::span[1]/span/span[2]")
	private List<WebElement> wbDiscountedPriceLbls;
	
	@FindBy(id="add-to-cart-button")
	private WebElement wbAddToCartBtn;
	

	

	
	

	public ProductPage(FrameworkContext context) {
		super(context);
		this.browser = context.getBrowser();
		
	}
	
	private static Logger log = LogManager.getLogger(ProductPage.class);
	
	
	public double getDiscountedPrice()
	{
		String discountedPrice = getText(wbDiscountedPriceLbls.get(0));
		log.info("Discount Price: {}",discountedPrice);
		return Double.parseDouble(discountedPrice);
	}
	
	public void addToCart()
	{
		waitForElementClickable(wbAddToCartBtn).click();	
	}
	
	
	
}
