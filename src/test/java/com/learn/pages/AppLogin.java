package com.learn.pages;

import com.learn.utilities.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AppLogin {
    WebDriver driver;
    public AppLogin(WebDriver appDriver){
        this.driver = appDriver;
    }

    @FindBy(id = "user-name") WebElement username;
    @FindBy(id = "password") WebElement password;
    @FindBy(id = "login-button") WebElement loginButton;
    @FindBy(xpath = "//span[contains(text(),'Products')]") WebElement pageTitle;

    /*public void printValues(){
        System.out.println(username);
        System.out.println(password);
        System.out.println(loginButton);
    }*/

    public void login(String AppUsername, String AppPassword){
        username.sendKeys(AppUsername);
        password.sendKeys(AppPassword);
        loginButton.click();
        Helper.getScreenshot(driver);
        Assert.assertTrue(pageTitle.isDisplayed());
    }
}
