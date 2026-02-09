Feature: Achat

  Background: Connexion avec des identifiants valides à SauceDemo
    Given l'utilisateur est sur la page de connexion
    When  il saisit le login "standard_user" et le mot de passe "secret_sauce"

  Scenario: Ajout d’un produit au panier
    Given  il ajoute un produit au panier
    When il va dans le panier
    Then il vérifie que le produit est bien ajouté