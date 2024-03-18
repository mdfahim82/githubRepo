package com.amazon.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.utils.FrameworkContext;

public class ProductPage extends CommonPage{
	
	@FindBy(id="productTitle")
	private WebElement wbProductTitleLbl;
	
	@FindBy(id="nav-search-submit-button")
	private WebElement wbSearchBtn;
	
	@FindBy(xpath="//span[@id='taxInclusiveMessage']/preceding-sibling::span[1]/span/span[2]")
	private List<WebElement> wbDiscountedPriceLbls;
	
	@FindBy(name="submit.add-to-cart")
	private WebElement wbAddToCartBtn;
	
	@FindBy(id="attachSiNoCoverage-announce")
	private WebElement wbSkipAddOnBtn;
	
	

	public ProductPage(FrameworkContext context) {
		super(context);
	}
	
	private static Logger log = LogManager.getLogger(ProductPage.class);
	
	
	public double getDiscountedPrice()
	{
		switchWindow();
		String discountedPrice = getText(wbDiscountedPriceLbls.get(0));
		log.info("ProductPage Discounted Price: {}",discountedPrice);
		return parseToDouble(discountedPrice);
	}
	
	public void addToCart()
	{
		scrollIntoView(wbAddToCartBtn);
		jsClick(wbAddToCartBtn);
		
		try
		{
			jsClick(wbSkipAddOnBtn);
		}
		catch(NoSuchElementException e)
		{
			log.info("No Addon Displayed");
		}
	}
	
	
	
}
