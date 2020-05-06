package com.lib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Maven_Demo1 

{
	@Test
	public void login_Ebay()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Public\\Downloads\\Eclipse\\geckodriver-v0.23.0-win64\\geckodriver.exe");
		WebDriver Driver=new FirefoxDriver();
		//Driver.get("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=");
		Driver.get("https://freecrm.com/");
		Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Driver.manage().window().maximize();
		String title=Driver.getTitle();
		String eTitle="Free CRM #1 cloud software for any business large or small";
		System.out.println("the title is "+title);
		Assert.assertEquals(title, eTitle);
		System.out.println("Git integration2");
		Driver.close();
		
		
	}

}
