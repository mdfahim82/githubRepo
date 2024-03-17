package com.amazon.pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.utils.FrameworkContext;

public abstract class CommonPage extends Page{
	
	private WebDriver browser;


	public CommonPage(FrameworkContext context) {
		super(context);
		this.browser = context.getBrowser();
		
	}
	
	private static Logger log = LogManager.getLogger(CommonPage.class);
	
	
	protected double parseToDouble(String text)
	{
		text = text.replaceAll(",","");
		return Double.parseDouble(text);
	}

}
