pipeline {
    agent any

    parameters{
       string(name: 'SELENIUM_BROWSER', defaultValue:'CHROME')
    }

    triggers {
    cron('05 12 * * 1-5')
    }

    stages {

        stage('Checkout') {
            steps {
                // Si ton projet n'est PAS sur Git, on saute cette étape
                echo 'Projet local - pas de checkout Git'
            }
        }


        stage('Build & Test') {
            steps {
                echo 'Execution des tests Cucumber via Maven...'
                    bat 'mvn test'
            }
        }
    }


    post {

        success {
            echo 'Tests exécutés avec succès 🎉'
        }

        failure {
            echo 'Des tests ont échoué ❌'
        }
    }
}