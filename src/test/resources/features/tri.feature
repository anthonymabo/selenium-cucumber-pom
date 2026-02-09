Feature: Tri

  Background: Connexion avec des identifiants valides à SauceDemo
    Given l'utilisateur est sur la page de connexion
    When  il saisit le login "standard_user" et le mot de passe "secret_sauce"

  Scenario: Ajout d’un produit au panier
