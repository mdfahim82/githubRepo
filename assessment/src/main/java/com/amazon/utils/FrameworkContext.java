package com.amazon.utils;

import org.openqa.selenium.WebDriver;

public class FrameworkContext {

	private WebDriver _browser = null;
	private String _application_URL = null;
	private int defaultWaitTime ;

	public int getDefaultWaitTime() {
		return defaultWaitTime;
	}

	public void setDefaultWaitTime(int defaultWaitTime) {
		this.defaultWaitTime = defaultWaitTime;
	}

	public String getApplication_URL() {
		return _application_URL;
	}

	public void setApplication_URL(String application_URL) {
		_application_URL = application_URL;
	}

	public WebDriver getBrowser() {
		return _browser;
	}

	public void setBrowser(WebDriver browser) {
		this._browser = browser;
	}

}
