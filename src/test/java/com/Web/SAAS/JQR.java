package com.Web.SAAS;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Web.Utility.allUtility;

public class JQR {
	public ChromeDriver wdriver;
	public FileInputStream file;
	public Properties pro;

	@Test

	public void Apilog() throws Exception {
		allUtility util = new allUtility();
		pro = util.loadPropertiesFile();
		wdriver = util.initializeWebDriver();
		util.response();
		util.navigateToUrl(pro.getProperty("jqrurl"));
		util.sendTextByWeb_Xpath("//input[@type='email']", pro.getProperty("jqrusername"));
		util.sendTextByWeb_Xpath("//input[@type='password']", pro.getProperty("jqrpassword"));
		util.clickWeb_ElementByXpath("//button[contains(text(),'Log In')]");
		util.clickWeb_ElementById("/product");
		util.clickWeb_ElementByXpath("//button[contains(@class,'inline-flex justify-cente w-12 h-12 rounded-full')]");
		util.clickWeb_ElementByXpath(
				"(//button[contains(@class,'absolute inset-y-0 right-0 flex items-center pr-2')])[1]");
		util.clickWeb_ElementByXpath("//li[contains(@class,'relative cursor-default select-none py-2.5 pl-10')]");
		util.clickWeb_ElementByXpath(
				"(//button[contains(@class,'absolute inset-y-0 right-0 flex items-center pr-2')])[2]");
		util.clickWeb_ElementByXpath("(//li[contains(@class,'relative cursor-default select-none py-2.5 pl-10')])[1]");
		util.sendTextByWeb_Id("Product Name", "Test JQR");
		Random random = new Random();
		int randomSixDigit = 100000 + random.nextInt(900000);
		String productCode = Integer.toString(randomSixDigit);
		util.sendTextByWeb_Id("Product Code", productCode);
		util.sendTextByWeb_Id("Qty", "1");
		util.sendTextByWeb_Id("Value", "2999");
		util.sendTextByWeb_Id("Product mrp", "2999");
		util.sendTextByWeb_Id("Product Description", "This is a Test Data");
		JavascriptExecutor js = (JavascriptExecutor) wdriver;
		js.executeScript("document.getElementById('dropzone-file').style.display='block';");
		util.sendTextByWeb_Xpath(
				"//label[contains(@class,'flex flex-col items-center justify-center w-full h-44 border-2')]",
				"C:\\Users\\ganes\\eclipse-workspace\\webAutomation\\Image\\JQR_Add_product.jpg");
		util.sendTextByWeb_Id("dropzone-file",
				"C:\\Users\\ganes\\eclipse-workspace\\webAutomation\\Image\\JQR_Add_product.jpg");
		util.clickWeb_ElementByXpath("//button[@type='submit']");
		String Status = null;
		String Pointstatus = null;
		List<WebElement> cell = wdriver.findElements(By.xpath("//div[@role='cell']"));
		int row = cell.size() / 21;
		for (int i = 0; i < row; i++) {
			int elementposition = i * 21 + 3;
			int pointactive = i * 21 + 15;
			int statusactive = i * 21 + 17;
			System.out.println(cell.get(elementposition).getText());
			if (cell.get(elementposition).getText().equals(productCode)) {
				System.out.println(cell.get(pointactive).getText());
				System.out.println(cell.get(statusactive).getText());

				switch (cell.get(pointactive).getText()) {
				
				case "Active": {
					Reporter.log("Status of Point Active is Active State Changing to In-Active",true);
					WebElement element = cell.get(pointactive);
					((JavascriptExecutor) wdriver).executeScript("arguments[0].click();", element);
					 Pointstatus="In-Active";
					break;
				}
				case "In-Active": {
					Reporter.log("Status of Point Active is In-Active State Changing to Active",true);
					WebElement element = cell.get(pointactive);
					((JavascriptExecutor) wdriver).executeScript("arguments[0].click();", element);
					 Pointstatus="Active"; 
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + cell.get(pointactive).getText());
				}
				
				switch (cell.get(statusactive).getText()) {
				case "Active": {
					Reporter.log("Status is Active State Changing to In-Active",true);
					WebElement element =cell.get(statusactive);
					((JavascriptExecutor) wdriver).executeScript("arguments[0].click();", element);
					Status="In-Active";
					break;
				}
				case "In-Active": {
					Reporter.log("Status is In-Active Changing to Active",true);
					WebElement element =cell.get(statusactive);
					((JavascriptExecutor) wdriver).executeScript("arguments[0].click();", element);
					Status="Active";
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + cell.get(statusactive).getText());
				}

				break;
			}
		}

		List<WebElement> cellafter_action = wdriver.findElements(By.xpath("//div[@role='cell']"));
		int rowafter_action = cellafter_action.size() / 21;
		for (int k = 0; k < rowafter_action; k++) {
			int elementpositionafter_action = k * 21 + 3;
			int pointactiveafter_action = k * 21 + 15;
			int statusactiveafter_action = k * 21 + 17;
			int delete = k * 21 + 20;
			Reporter.log("Product Code: "+cellafter_action.get(elementpositionafter_action).getText(),true);
			Reporter.log("Point Active: "+cellafter_action.get(pointactiveafter_action).getText(),true);
			Reporter.log("Status: "+cellafter_action.get(statusactiveafter_action).getText(),true);
			
//			if(cell.get(elementpositionafter_action).getText().equals(productCode)) {
//				Assert.assertEquals(cellafter_action.get(pointactiveafter_action).getText(),Pointstatus);
//				Assert.assertEquals(cellafter_action.get(statusactiveafter_action).getText(),Status);
//			}
			
			if(cell.get(elementpositionafter_action).getText().equals(productCode)) {
				cell.get(delete).click();
				Alert productdelete= wdriver.switchTo().alert();
				productdelete.accept();
				Reporter.log("Delete Button Get Clicked ",true);
				break;
			}
		}
		wdriver.navigate().refresh();
		Thread.sleep(1000);
		List<WebElement> cellafter_deleteaction = wdriver.findElements(By.xpath("//div[@role='cell']"));
		int rowafter_deleteaction = cellafter_deleteaction.size() / 21;
		int a=0;
		for (int k = 0; k < rowafter_deleteaction; k++) {
			int elementpositionafter_action = k * 21 + 3;
			if(cellafter_deleteaction.get(elementpositionafter_action).getText().equals(productCode)) {
				a=a+1;
			}
		}
		if(a==1) {
			Reporter.log("Delete Action Not Performed",true);
		}else {
			Reporter.log("No Data found Product Get Deleted",true);
		}
		
//		util.checkPagination("//div[@role='cell']", 21, 3);
		

	}

}
