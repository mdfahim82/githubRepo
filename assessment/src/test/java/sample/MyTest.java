package sample;

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
	
	protected WebDriver browser;
	protected ProjectProperty properties = ProjectProperty.getInstance();	
	protected FrameworkContext frameworkContext = new FrameworkContext();
	private static Logger log = LogManager.getLogger(MyTest.class);
	
	private LandingPage _landingPage = null;
	private ProductPage _productPage = null;
	private CartPage _cartPage = null;
	
	private List<WebElement> _resultsElements =null;
	
	private int resultIndex;
	private double productPagePrice;
	private double cartPagePrice;
	private double cartPageSubtotal;

	public MyTest() {
		WebDriverManager driverManager = new WebDriverManager();
		this.browser = driverManager.initDriver(properties.getBrowserChoice());		
		frameworkContext.setBrowser(browser);
		frameworkContext.setApplication_URL(properties.getApplicationURL());
		frameworkContext.setDefaultWaitTime(properties.getDefaultWaitTime());
		log.info("Driver is intialized");
	}

	@Test
	public void justTest() {
		
		String searchProduct = "KeyBoard";
		
		_landingPage = new LandingPage(frameworkContext);
		_landingPage.NavigateToLandingPage();
		log.info("Navigated to Landing Page");
		
		_resultsElements = _landingPage.searchProductGetResults(searchProduct);
		log.info("Total results count: {}",_resultsElements.size());
		
		
		this.resultIndex = 1;
		log.info("Result Index: "+resultIndex);
		_landingPage.selectProduct(_resultsElements.get(resultIndex-1));
		
		
		_productPage = new ProductPage(frameworkContext);
		productPagePrice = _productPage.getDiscountedPrice();
		_productPage.addToCart();
		
		
		 _cartPage = new CartPage(frameworkContext);
		 _cartPage.navigateToCartPage();
		 cartPagePrice = _cartPage.getPriceFromCart(resultIndex);
		 cartPageSubtotal = _cartPage.getSubTotalPriceFromCart();
		 
		 
		 Assert.assertEquals(productPagePrice, cartPagePrice);
		 Assert.assertEquals(productPagePrice, cartPageSubtotal);
		 
		 
		 
		 
		 frameworkContext.getBrowser().quit();
			log.info("Driver is closed");
	}

}
