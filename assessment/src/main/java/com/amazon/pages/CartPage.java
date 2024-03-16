package com.amazon.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.utils.FrameworkContext;

public class CartPage extends CommonPage{
	
	private WebDriver browser;
	

	@FindBy(xpath="//div[@class='sc-item-price-block']/div/div/div/span")
	private List<WebElement> wbCartPerProductPriceLbls;
	
	@FindBy(xpath="//span[@id='sc-subtotal-amount-activecart']/span")
	private WebElement wbCartSubTotalLbl;
	
	@FindBy(xpath="//span[@id='attach-sidesheet-view-cart-button-announce']/preceding-sibling::input")
	private WebElement wbCartBtn;
	

	public CartPage(FrameworkContext context) {
		super(context);
		this.browser = context.getBrowser();
	}
	
	private static Logger log = LogManager.getLogger(CartPage.class);
	
	
	public void navigateToCartPage()
	{
		waitForElementClickable(wbCartBtn).click();
	}
	
	public double getPriceFromCart(int poductIndex)
	{
		String price = getText(wbCartPerProductPriceLbls.get(poductIndex-1));
		log.info("Price: {}",price);
		return Double.parseDouble(price);
	}
	
	
	public double getSubTotalPriceFromCart()
	{
		String subTotalPrice = getText(wbCartSubTotalLbl);
		log.info("subTotalPrice: {}",subTotalPrice);
		return Double.parseDouble(subTotalPrice);
	}
	
	
	
	
	
	
	
	
}
