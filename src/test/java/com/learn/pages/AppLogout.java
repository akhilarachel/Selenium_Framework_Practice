package com.learn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AppLogout {
    WebDriver driver;

    public AppLogout (WebDriver loDriver){
        this.driver = loDriver;
    }

    @FindBy(id = "react-burger-menu-btn") WebElement allMenu;
    @FindBy(xpath = "//a[@id='logout_sidebar_link']") WebElement logoutButton;
    @FindBy(id = "login-button") WebElement loginButton;

    public void logout(){
        allMenu.click();
        logoutButton.click();
        Assert.assertTrue(loginButton.isDisplayed());
    }
}
