package com.example.automation.steps;

import com.example.automation.configuration.Hooks;
import com.example.automation.pages.HomePage;
import com.example.automation.pages.LoginPage;
import com.example.automation.configuration.ConfigReader;
import com.example.automation.configuration.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.example.automation.configuration.DriverFactory.driver;


public class LoginSteps {

    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    public ConfigReader settings = new ConfigReader();


    @Given("l'utilisateur est sur la page de connexion")
    public void lutilisateurEstSurLaPageDeConnexion(){
        String url = settings.getProperty("url");
        loginPage.lutilisateurEstSurLaPageDeConnexion(url);
    }

    @When("il saisit le login {string} et le mot de passe {string}")
    public void ilSaisitLeLoginEtLeMotDePasse(String user, String pass){
        loginPage.ilSaisitLeLoginEtLeMotDePasse(user, pass);
    }





}
