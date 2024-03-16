package com.amazon.test;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.amazon.utils.FrameworkContext;
import com.amazon.utils.ProjectProperty;
import com.amazon.utils.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestMain {

	
	
	private static Logger log = LogManager.getLogger(TestMain.class);
	
	public Method testName;
	protected WebDriver browser;
	protected String testTrName;
	protected String testCaseName;
	protected String testSuiteName;
	
	protected ProjectProperty properties;
	
	protected FrameworkContext frameworkContext = new FrameworkContext();
	
	
	public final String statusPass = "PASS";
	public final String statusFail = "FAIL";
	
	
	public TestMain() {
		log.debug("Amazon Test Main():");

		log.trace("Load project.properties");
		properties = ProjectProperty.getInstance();

		log.info("Project properties:\n" + properties + "\n");
	}
	
	@BeforeTest
	public void initDriver()
	{
		WebDriverManager driverManager = new WebDriverManager();
		WebDriver browser = driverManager.initDriver(properties.getBrowserChoice());		
		frameworkContext.setBrowser(browser);
		frameworkContext.setApplication_URL(properties.getApplicationURL());
		frameworkContext.setDefaultWaitTime(properties.getDefaultWaitTime());
	}

	@AfterTest
	public void closeDriver()
	{
		frameworkContext.getBrowser().quit();
	}
}
