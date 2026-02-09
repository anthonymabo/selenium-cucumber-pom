package com.example.automation.pages;

import com.example.automation.configuration.ConfigReader;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage extends BasePage {

    private ConfigReader settings;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpack;

    @FindBy(xpath = "//div[normalize-space()='Sauce Labs Backpack']")
    private WebElement backpack;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement cart;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private WebElement item;


    public void ilAjouteUnProduitAuPanier() {
        WebElement eltButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        eltButton.click();
    }

    public void ilVaDansLePanier() {
        WebElement eltButton = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        eltButton.click();
    }

    public void ilVerifieqQueLeProduitEstBienAjoute(){
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item_name']")));
    }


    public boolean ilEstRedirigeVersLaPageDAccueil() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains("app_logo"));
    }


}
