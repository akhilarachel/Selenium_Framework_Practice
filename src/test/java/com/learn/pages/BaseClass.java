package com.learn.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learn.utilities.BrowserSettings;
import com.learn.utilities.ConfigDataReader;
import com.learn.utilities.ExcelTestDataReader;
import com.learn.utilities.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class BaseClass {
    public WebDriver driver;
    public BrowserSettings bs = new BrowserSettings();
    public ExcelTestDataReader exReader;
    public ConfigDataReader configReader;
    public ExtentReports report;
    public String randomFilename;
    public ExtentTest logger;

    @BeforeSuite
    public void setUpSuite(){
        Reporter.log("Test config is being prepared" , true);
        exReader = new ExcelTestDataReader();
        configReader = new ConfigDataReader();
        randomFilename = Helper.getRandomValue();
        ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/TestReport_"+randomFilename+".html"));
        report = new ExtentReports();
        report.attachReporter(htmlReport);
        Reporter.log("Test can be started" , true);
    }

    @Parameters({"browser","url"})
    @BeforeClass
    public void setUp(String browser, String url){
        Reporter.log("Launching the application" , true);
        //driver = bs.openBrowser(configReader.getBrowserValue(), configReader.getTestUrl());
        driver = bs.openBrowser(browser, url);
        //Helper.getScreenshot(driver);
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
        Reporter.log("Login is successful" , true);
    }

    @AfterClass
    public void tearDown(){
        bs.quitBrowser(driver);
    }

    @AfterMethod
    public void finalTearDown(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            logger.fail("Testcase failed: ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.getScreenshot(driver)).build());
        } else if (result.getStatus() == ITestResult.SUCCESS){
            logger.pass("Testcase passed: ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.getScreenshot(driver)).build());
        } else if (result.getStatus() == ITestResult.SKIP){
            logger.skip("Testcase skipped: ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.getScreenshot(driver)).build());
        }
        report.flush();
    }
}
