package com.Web.SAAS;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.Web.Utility.allUtility;

public class pagination {
	public ChromeDriver wdriver;
	public FileInputStream file;
	public Properties pro;
	@Test
	
	public void test_Pagination() throws Exception {
		allUtility util = new allUtility();
		pro = util.loadPropertiesFile();
		wdriver = util.initializeWebDriver();
		util.response();
		util.navigateToUrl(pro.getProperty("jqrurl"));
		util.sendTextByWeb_Xpath("//input[@type='email']", pro.getProperty("jqrusername"));
		util.sendTextByWeb_Xpath("//input[@type='password']", pro.getProperty("jqrpassword"));
		util.clickWeb_ElementByXpath("//button[contains(text(),'Log In')]");
		util.clickWeb_ElementById("/printQrList");
//		util.checkPagination("//div[@role='cell']", 11, 1);

	}
	

}
