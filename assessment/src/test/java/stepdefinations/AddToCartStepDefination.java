package stepdefinations;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.amazon.pages.CartPage;
import com.amazon.pages.LandingPage;
import com.amazon.pages.ProductPage;
import com.amazon.utils.FrameworkContext;
import com.amazon.utils.ProjectProperty;
import com.amazon.utils.WebDriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;




public class AddToCartStepDefination{

	protected WebDriver _browser;
	protected ProjectProperty _properties = ProjectProperty.getInstance();	
	protected FrameworkContext _frameworkContext = new FrameworkContext();
	private static Logger _log = LogManager.getLogger(AddToCartStepDefination.class);
	
	private LandingPage _landingPage = null;
	private ProductPage _productPage = null;
	private CartPage _cartPage = null;
	
	private List<WebElement> _resultsElements =null;
	
	private double productPagePrice;
	private double cartPageSubtotal;
	private double productPageSubtotal;
	
	private HashMap<String,Double> _productPagePrices = new LinkedHashMap<String,Double>();
	private HashMap<String,Double> _cartPagePrices = new LinkedHashMap<String,Double>();
	
	
	@Before
	public void initDriver()
	{
		WebDriverManager driverManager = new WebDriverManager();
		this._browser = driverManager.initDriver(_properties.getBrowserChoice());		
		_frameworkContext.setBrowser(_browser);
		_frameworkContext.setApplication_URL(_properties.getApplicationURL());
		_frameworkContext.setDefaultWaitTime(_properties.getDefaultWaitTime());
		_log.info("Driver is intialized");
	}

	@After
	public void closeDriver()
	{
		_frameworkContext.getBrowser().quit();
		_log.info("Test completed... Closing Broser");
	}
	
	@Given("user is on Amazon Landing Page")
	public void user_is_on_amazon_landing_page() {
		_landingPage = new LandingPage(_frameworkContext);
		boolean status = _landingPage.NavigateToLandingPage();
		assertTrue(status,"CAPTCHA cannot be automated. Please stop and retry execution");
		_log.info("Navigated to Landing Page");
	}
	
	@When("user search {string} in search filed")
	public void user_search_in_search_filed(String productName) {
		_log.info("Product Name: " + productName);
		
		_resultsElements = _landingPage.searchProductGetResults(productName);
	}

	@Then("user select {int} product")
	public void user_select_product(Integer resultIndex) {
		_log.info("Product Selection Index: "+resultIndex);
		
		_landingPage.selectProduct(_resultsElements.get(resultIndex-1));
	}

	@And("user add {string} product to cart")
	public void user_add_product_to_cart(String productName) {
		_productPage = new ProductPage(_frameworkContext);
		
		productPagePrice = _productPage.getDiscountedPrice();
		_productPagePrices.put(productName, productPagePrice);
		_productPage.addToCart();
		
	}

	@Then("compare price of {string} in product page with cart page")
	public void compare_price_in_product_page_with_cart_page(String productName) {
		 _cartPage = new CartPage(_frameworkContext);
		 _cartPage.navigateToCartPage();
		 
		 double cartPagePrice = _cartPage.getPriceFromCart();
		 _cartPagePrices.put(productName, cartPagePrice);
		 cartPageSubtotal = _cartPage.getSubTotalPriceFromCart();
		 _log.info("Price of {} in ProductPage {} and in CartPage {}",productName,productPagePrice, cartPagePrice);
		 Assert.assertEquals(productPagePrice, cartPagePrice);
		
	}

	@And("compare sub total with product page")
	public void compare_sub_total_with_product_page() {
		for(double value : _productPagePrices.values())
			productPageSubtotal += value;
		_log.info("SubTotal of ProductPage {} and CartPage {}",productPageSubtotal, cartPageSubtotal);
		Assert.assertEquals(productPageSubtotal, cartPageSubtotal);
	}
	
	
	@Then("compare price all items in product page with cart page")
	public void compare_price_all_items_in_product_page_with_cart_page() {
		for(String item : _productPagePrices.keySet())
		{
			_log.info("Price of: {} in ProductPage {} and in CartPage {}",item,_productPagePrices.get(item),_cartPagePrices.get(item));
			Assert.assertEquals(_productPagePrices.get(item), _cartPagePrices.get(item));
		}
			
	}

}
