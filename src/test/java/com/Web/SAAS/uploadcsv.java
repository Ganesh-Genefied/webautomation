package com.Web.SAAS;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Web.Utility.allUtility;
import com.opencsv.CSVWriter;
//@Listeners(com.Web.Utility.MyListener.class)
public class uploadcsv {
	public ChromeDriver wdriver;
	public FileInputStream file;
	public Properties pro;

	@Test

	public void csvupload() throws Exception {

		String filePath = "C:\\Users\\ganes\\eclipse-workspace\\webAutomation\\CSVupload\\addJQRproductlist.csv"; 
		String[] header = { "category_id", "category_name", "name", "description", "product_code", "mrp", "size",
				"color" };
		Random random = new Random();
		int random6Digit = 100000 + random.nextInt(900000);
		String str = Integer.toString(random6Digit);
		System.out.println(random6Digit);
		String[] rowData = { "6", "Gents", "Firefly", "Test Description", str, "2999", "7", "Black" };
		CSVWriter writer = new CSVWriter(new FileWriter(filePath));
		writer.writeNext(header);
		writer.writeNext(rowData);
		System.out.println("CSV file created successfully.");
		writer.close();
		allUtility util = new allUtility();
		pro = util.loadPropertiesFile();
		wdriver = util.initializeWebDriver();
		util.response();
		util.navigateToUrl(pro.getProperty("jqrurl"));
		util.sendTextByWeb_Xpath("//input[@type='email']", pro.getProperty("jqrusername"));
		util.sendTextByWeb_Xpath("//input[@type='password']", pro.getProperty("jqrpassword"));
		util.clickWeb_ElementByXpath("//button[contains(text(),'Log In')]");
		util.clickWeb_ElementById("/product");
		util.clickWeb_ElementByXpath("//button[normalize-space()='Add Product Csv']"); 
		util.clickWeb_ElementById("headlessui-combobox-button-:r6:");
		util.clickWeb_ElementById("headlessui-combobox-option-:r8:");
		JavascriptExecutor js = (JavascriptExecutor) wdriver;
		js.executeScript("document.getElementById('dropzone-file').style.display='block';");
		util.sendTextByWeb_Xpath(
				"//label[contains(@class,'flex flex-col items-center justify-center w-full h-20 border-2 bord')]",
				filePath);
//		util.sendTextByWeb_Id("dropzone-file",
//				filePath);
		wdriver.findElement(By.id("dropzone-file")).sendKeys(filePath);
		util.clickWeb_ElementByXpath("//button[@type='submit'][normalize-space()='Add']");
		Thread.sleep(2500);
		Alert alert = wdriver.switchTo().alert();
		alert.accept();
		
		Thread.sleep(500);
		wdriver.navigate().refresh();

	}
}
