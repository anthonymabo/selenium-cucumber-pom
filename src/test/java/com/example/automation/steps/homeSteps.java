package com.example.automation.steps;

import com.example.automation.configuration.ConfigReader;
import com.example.automation.pages.HomePage;
import com.example.automation.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.example.automation.configuration.DriverFactory.driver;

public class homeSteps {

    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    public ConfigReader settings = new ConfigReader();

    @Then("il est redirigé vers la page d'accueil")
    public boolean ilEstRedirigeVersLaPageDAccueil(){
        homePage.ilEstRedirigeVersLaPageDAccueil();
        return true;
    }

    @When("il ajoute un produit au panier")
    public void ilAjouteUnProduitAuPanier() {
        homePage.ilAjouteUnProduitAuPanier();
    }

    @Then("il va dans le panier")
    public void ilVaDansLePanier(){

        homePage.ilVaDansLePanier();
    }

    @And("il verifie que le produit est bien ajoute")
    public void ilVerifieqQueLeProduitEstBienAjoutee() {
        homePage.ilVerifieqQueLeProduitEstBienAjoute();
    }
}

