package com.Web.SAAS;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.Web.Utility.allUtility;

public class paginationNetwork {
	public ChromeDriver wdriver;
	public FileInputStream file;
	public Properties pro;

	@Test

	public void csvupload() throws Exception {

		allUtility util = new allUtility();
		pro = util.loadPropertiesFile();
		wdriver = util.initializeWebDriver();
		AtomicReference<String> responseBody = util.paginationresponse("https://saas-api-dev.genefied.in/api/app/zone/count?zoneId=0");
//		AtomicReference<String> responseBody = paginationresponse("https://saas-api-dev.genefied.in/api/tenant/products/count");
		util.navigateToUrl(pro.getProperty("jqrurl"));
		util.sendTextByWeb_Xpath("//input[@type='email']", pro.getProperty("jqrusername"));
		util.sendTextByWeb_Xpath("//input[@type='password']", pro.getProperty("jqrpassword")); 
		util.clickWeb_ElementByXpath("//button[contains(text(),'Log In')]");
		util.clickWeb_ElementById("/user/retailer");
		util.checkPagination(41, 3, responseBody, "total");
	}

	

}
