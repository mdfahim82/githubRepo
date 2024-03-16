package com.amazon.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MyPage {
	
	

	public MyPage() {
	}

	private static Logger log = LogManager.getLogger(MyPage.class);

	public static void main(String[] args) {

		display();
		
	}
	
	public static void display()
	{
		log.info("Hello World");
	}

}
