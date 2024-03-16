package com.amazon.utils;

import org.openqa.selenium.WebDriver;

public class FrameworkContext {

	private WebDriver browser = null;
	private String Application_URL = null;
	private int defaultWaitTime ;

	public int getDefaultWaitTime() {
		return defaultWaitTime;
	}

	public void setDefaultWaitTime(int defaultWaitTime) {
		this.defaultWaitTime = defaultWaitTime;
	}

	public String getApplication_URL() {
		return Application_URL;
	}

	public void setApplication_URL(String application_URL) {
		Application_URL = application_URL;
	}

	public WebDriver getBrowser() {
		return browser;
	}

	public void setBrowser(WebDriver browser) {
		this.browser = browser;
	}

}
