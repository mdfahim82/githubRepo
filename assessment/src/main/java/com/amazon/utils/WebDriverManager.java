package com.amazon.utils;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {

	private WebDriver _baseDriver = null;
	private Logger _log = LogManager.getLogger(WebDriverManager.class);

	public WebDriver initDriver(String platform) {
		switch (platform) {
		case Constants.FIREFOX_BROWSER:
			_baseDriver = new FirefoxDriver();
			break;
		case Constants.CHROME_BROWSER:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			_baseDriver = new ChromeDriver(options);
			break;
		case Constants.EDGE_BROWSER:
			_baseDriver = new EdgeDriver();
			break;

		default:
			_baseDriver = null;
		}

		if (_baseDriver != null) {
			_log.info("{} is initiated ", platform);
			_baseDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			_baseDriver.manage().window().maximize();
		}

		return _baseDriver;
	}
}
