package com.amazon.pages;

import com.amazon.utils.FrameworkContext;

public abstract class CommonPage extends Page{
	
	public CommonPage(FrameworkContext context) {
		super(context);
		
	}
	
	protected double parseToDouble(String text)
	{
		text = text.replaceAll(",","");
		return Double.parseDouble(text);
	}

}
