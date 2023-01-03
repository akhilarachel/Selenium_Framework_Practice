package com.learn.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BrowserSettings {
    public WebDriver driver;
    public WebDriver openBrowser (String browserName, String appURL) {
        //System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver");
        if(browserName.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("FireFox")){
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(appURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    public void quitBrowser (WebDriver driver) {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
