package com.Web.Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class MyListener implements ITestListener{
	public ChromeDriver wdriver;
    @Override
    public void onTestStart(ITestResult result) {
    	Reporter.log("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	Reporter.log("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	Reporter.log("Test failed: " + result.getName());
    	File screenshot = ((TakesScreenshot) wdriver).getScreenshotAs(OutputType.FILE);
        File destination = new File("C:\\Users\\ganes\\eclipse-workspace\\webAutomation\\ScreenShoots\\screenshot.png");
        try {
			FileUtils.copyFile(screenshot, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("Screenshot saved successfully!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	Reporter.log("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
       
    }

    @Override
    public void onStart(ITestContext context) {
    	Reporter.log("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
    	Reporter.log("Test suite finished: " + context.getName());
    }
}
	


