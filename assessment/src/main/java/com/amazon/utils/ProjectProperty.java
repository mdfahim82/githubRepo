 package com.amazon.utils;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ProjectProperty {
	
	private static ProjectProperty projectProperty = null;
	private Properties prop = null;
	
	private static Logger log = LogManager.getLogger(ProjectProperty.class);


	private ProjectProperty() {
		prop = new Properties();
		String projectPath = System.getProperty("user.dir");
		try (FileInputStream fIS = new FileInputStream(projectPath+"/src/test/resources/"+Constants.PROJECT_PROPERTY_FILE_PATH)) {
			prop.load(fIS);
		} catch (Exception e) {
			log.error(Arrays.toString(e.getStackTrace()));
		}
	}

	public static ProjectProperty getInstance() {
		if (projectProperty == null) {
			projectProperty = new ProjectProperty();
		}
		return projectProperty;
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

	public String getBrowserChoice() {
		return getProperty(Constants.BROWSER);
	}
	
	public String getApplicationURL() {
		return getProperty(Constants.APPLICATION_URL);
	}
	
	public String getTestDataWorkBookName() {
		return getProperty(Constants.TEST_DATA_WORKBOOK);
	}
	
	public int getDefaultWaitTime() {
		return Integer.parseInt(getProperty(Constants.DEFAULT_WAIT_TIME));
	}
}
