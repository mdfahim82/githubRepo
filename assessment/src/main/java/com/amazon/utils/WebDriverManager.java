package com.amazon.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager {

	WebDriver baseDriver = null;
	ProjectProperty prop = ProjectProperty.getInstance();
	protected String testName = null;
	static int count=0;

	public WebDriverManager() {

	}

	private Logger log = LogManager.getLogger(WebDriverManager.class);

	public WebDriver initDriver(String platform) {
		count++;
		switch (platform) {
		case Constants.FIREFOX_BROWSER:
			baseDriver = new FirefoxDriver();
			break;
		case Constants.CHROME_BROWSER:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito"); 
			baseDriver = new ChromeDriver(options);
			break;
		case Constants.EDGE_BROWSER:
			baseDriver = new EdgeDriver();
			break;

		default:
			baseDriver = null;
		}
		
		log.info("{} is initiated with count {}",platform, count);
		if (baseDriver != null)
		{
			baseDriver.manage().window().maximize();
//			baseDriver.manage().deleteAllCookies();
		}
			
		return baseDriver;
	}
}
