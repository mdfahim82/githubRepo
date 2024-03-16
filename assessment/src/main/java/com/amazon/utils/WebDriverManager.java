package com.amazon.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {

	WebDriver baseDriver = null;
	ProjectProperty prop = ProjectProperty.getInstance();
	protected String testName = null;

	public WebDriverManager() {

	}

	private Logger log = LogManager.getLogger(WebDriverManager.class);

	public WebDriver initDriver(String platform) {
		switch (platform) {
		case Constants.FIREFOX_BROWSER:
			baseDriver = new FirefoxDriver();
			break;
		case Constants.CHROME_BROWSER:
			baseDriver = new ChromeDriver();
			break;
		case Constants.EDGE_BROWSER:
			baseDriver = new EdgeDriver();
			break;

		default:
			baseDriver = null;
		}
		if (baseDriver != null)
			baseDriver.manage().window().maximize();
		return baseDriver;
	}

	public WebDriver getChromeDriver() {
		return new ChromeDriver();
	}

}
