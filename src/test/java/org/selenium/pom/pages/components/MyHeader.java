package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class MyHeader extends BasePage {
    private final By storeMenuLink = By.xpath("//*[@id=\"menu-item-1227\"]/a");


    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu() {
        WebElement e = waitForElementToBeVisible(storeMenuLink);
        e.click();
        return new StorePage(driver);
    }
}
