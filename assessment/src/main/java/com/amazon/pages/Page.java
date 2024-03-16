package com.amazon.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	
	public WebElement waitForElementClickable(WebElement element)
	{
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementVisibility(WebElement element)
	{
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public List<WebElement> waitForAllEmentsVisible(WebElement element) {
		
		return wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void enterText(WebElement element, String text)
	{
		waitForElementClickable(element).clear();
		element.click();
		element.sendKeys(text);
	}
	
	public void click(WebElement element)
	{
		waitForElementClickable(element).click();
	}
	
	public String getText(WebElement element)
	{
		return waitForElementVisibility(element).getText();
	}
	
}
