Feature: Authentification

  Scenario Outline: Connexion avec des identifiants valides à SauceDemo
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "<username>" et le mot de passe "<passeword>"
    Then il est redirigé vers la page d'accueil

    Examples:
      | username      | passeword    |
      | standard_user | secret_sauce |
      | standard_user | secret       |
      | problem_user | secret_sauce  |





