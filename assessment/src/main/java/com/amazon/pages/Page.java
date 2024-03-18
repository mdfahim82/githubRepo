package com.amazon.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.utils.FrameworkContext;

public abstract class Page {

	private WebDriver _browser;
	private int defaultWaitTime ;
	private WebDriverWait _wait;

	public Page(FrameworkContext context) {
		this._browser = context.getBrowser();
		PageFactory.initElements(_browser, this);
		this.defaultWaitTime = context.getDefaultWaitTime();
		_wait = new WebDriverWait(_browser,  Duration.ofSeconds(defaultWaitTime));
	}

	
	protected WebElement waitForElementClickable(WebElement element)
	{
		return _wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected WebElement waitForElementVisibility(WebElement element)
	{
		return _wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected List<WebElement> waitForAllEmentsVisible(List<WebElement> elements) {
		
		return _wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	protected void enterText(WebElement element, String text)
	{
		waitForElementClickable(element);
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
		String oldWindowHandle = _browser.getWindowHandle();
		Set<String> allWindows = _browser.getWindowHandles();
		for (String window : allWindows)
		{
			if(!window.equals(oldWindowHandle))
			{
				_browser.close();
				_browser.switchTo().window(window);
			}
		}
	}
	
	
	protected void scrollIntoView(WebElement element)
	{
		((JavascriptExecutor) _browser).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	
	protected void jsClick(WebElement element)
	{
		((JavascriptExecutor) _browser).executeScript("arguments[0].click();", element);
	}
	
}
