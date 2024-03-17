package com.amazon.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.utils.FrameworkContext;

public abstract class Page {

	private WebDriver browser;
	private int defaultWaitTime ;
	private WebDriverWait wait;

	public Page(FrameworkContext context) {
		this.browser = context.getBrowser();
		PageFactory.initElements(browser, this);
		this.defaultWaitTime = context.getDefaultWaitTime();
		wait = new WebDriverWait(browser,  Duration.ofSeconds(defaultWaitTime));
	}

	private static Logger log = LogManager.getLogger(Page.class);
	
	
	protected WebElement waitForElementClickable(WebElement element)
	{
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected WebElement waitForElementVisibility(WebElement element)
	{
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected List<WebElement> waitForAllEmentsVisible(List<WebElement> elements) {
		
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	protected void enterText(WebElement element, String text)
	{
		waitForElementClickable(element);//.clear();
		element.click();
		element.sendKeys(text);
	}
	
	protected void click(WebElement element)
	{
		waitForElementClickable(element).click();
	}
	
	protected String getText(WebElement element)
	{
		String text = waitForElementVisibility(element).getText();
		if(text==null || text.isEmpty())
			text = element.getAttribute("innerHTML");
		return text;
	}
	
	protected void switchWindow()
	{
		String currentWindowHandle = browser.getWindowHandle();
		Set<String> allWindows = browser.getWindowHandles();
		for (String window : allWindows)
		{
			if(!window.equals(currentWindowHandle))
			{
				browser.switchTo().window(window);
			}
		}
	}
	
	
	protected void scrollIntoView(WebElement element)
	{
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	
	protected void jsClick(WebElement element)
	{
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", element);
	}
	
}
