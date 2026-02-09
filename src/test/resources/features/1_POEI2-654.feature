Feature: Test ok - Achat

	Background:
		#@POEI2-655
		Given l'utilisateur est sur la page de connexion
		    When  il saisit le login "standard_user" et le mot de passe "secret_sauce"

	@POEI2-654
	Scenario: Test ok - Achat
		Given  il ajoute un produit au panier
		    When il va dans le panier
		    Then il verifie que le produit est bien ajoute
		
