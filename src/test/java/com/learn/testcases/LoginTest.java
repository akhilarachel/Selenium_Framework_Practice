package com.learn.testcases;

import com.learn.pages.AppLogin;
import com.learn.pages.AppLogout;
import com.learn.pages.BaseClass;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
    @Test(priority = 1)
    public void loginApp(){
        logger = report.createTest("Login to CRM");
        AppLogin li = PageFactory.initElements(driver,AppLogin.class);
        logger.info("Launched the URL");
        li.login(exReader.getStringData("Login", 0, 0), exReader.getStringData("Login", 0, 1));
        logger.pass("Logged in successfully");
    }

    @Test(dependsOnMethods = "loginApp")
    public void logoutApp(){
        logger = report.createTest("Logging out");
        AppLogout lo = PageFactory.initElements(driver, AppLogout.class);
        lo.logout();
        logger.pass("Logged out successfully");
    }
}
