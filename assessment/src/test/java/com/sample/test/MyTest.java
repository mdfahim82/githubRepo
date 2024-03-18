package com.sample.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.pages.CartPage;
import com.amazon.pages.LandingPage;
import com.amazon.pages.ProductPage;
import com.amazon.utils.FrameworkContext;
import com.amazon.utils.ProjectProperty;
import com.amazon.utils.WebDriverManager;

public class MyTest {

	protected WebDriver _browser;
	protected ProjectProperty _properties = ProjectProperty.getInstance();
	protected FrameworkContext _frameworkContext = new FrameworkContext();
	private static Logger _log = LogManager.getLogger(MyTest.class);

	private LandingPage _landingPage = null;
	private ProductPage _productPage = null;
	private CartPage _cartPage = null;

	private List<WebElement> _resultsElements = null;

	private int resultIndex;
	private double productPagePrice;
	private double cartPagePrice;
	private double cartPageSubtotal;

	public MyTest() {
		WebDriverManager driverManager = new WebDriverManager();
		this._browser = driverManager.initDriver(_properties.getBrowserChoice());
		_frameworkContext.setBrowser(_browser);
		_frameworkContext.setApplication_URL(_properties.getApplicationURL());
		_frameworkContext.setDefaultWaitTime(_properties.getDefaultWaitTime());
		_log.info("Driver is intialized");
	}

	@Test
	public void justTest() {

		String searchProduct = "Laptop";
		resultIndex = 2;

		try {

			_landingPage = new LandingPage(_frameworkContext);
			_landingPage.NavigateToLandingPage();
			_log.info("Navigated to Landing Page");

			_resultsElements = _landingPage.searchProductGetResults(searchProduct);
			_log.info("Total results count: {}", _resultsElements.size());

			_log.info("Result Index: " + resultIndex);
			_landingPage.selectProduct(_resultsElements.get(resultIndex - 1));

			_productPage = new ProductPage(_frameworkContext);
			productPagePrice = _productPage.getDiscountedPrice();
			_productPage.addToCart();

			_cartPage = new CartPage(_frameworkContext);
			_cartPage.navigateToCartPage();
			cartPagePrice = _cartPage.getPriceFromCart();
			cartPageSubtotal = _cartPage.getSubTotalPriceFromCart();

			Assert.assertEquals(productPagePrice, cartPagePrice);
			Assert.assertEquals(productPagePrice, cartPageSubtotal);
		} catch (Exception e) {
			_log.info("Exception occured: {}", e.getMessage());
		} finally {
			_frameworkContext.getBrowser().quit();
			_log.info("Driver is closed");
		}

	}

}
