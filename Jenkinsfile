pipeline {
    agent any
    parameters {
       string(name: 'SELENIUM_BROWSER', defaultValue:'CHROME')
       string(name: 'TEST_PLAN_KEY', defaultValue: 'POEI2-654')
    }
    triggers { cron('05 12 * * 1-5') }

    stages {
        stage('Export Xray') {
            steps {
                script {
                    def token = bat(script: '@echo off \n curl -s -X POST -H "Content-Type: application/json" -d "{\\"client_id\\":\\"93B37FB647824B09A6FD0C59815625CC\\",\\"client_secret\\":\\"6487d186161b48ea2906cb415dc22b14b4f1d16602b36eb8b9cf9b0dcd680d55\\"}" https://xray.cloud.getxray.app/api/v2/authenticate', returnStdout: true).trim().replaceAll('"', '')


                    bat """
                        curl -H "Authorization: Bearer ${token}" "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=${params.TEST_PLAN_KEY}" -o f.zip
                    """
                    bat 'tar -xf features.zip -C src/test/resources/features'
                    bat 'del features.zip'
                }
            }
        }

        stage('Checkout') {
            steps {
                        // Si ton projet n'est PAS sur Git, on saute cette étape
                        echo 'Projet local - pas de checkout Git'
            }
        }

        stage('Build & Test') {
             steps {
                        echo 'Execution des tests Cucumber via Maven...'
                        dir('C:/Users/salim/OneDrive/Desktop/dev/POEI2033/CucumberPoei') {
                            bat 'mvn clean test'
                        }

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
