pipeline {
    agent any

    parameters{
       string(name: 'SELENIUM_BROWSER', defaultValue:'CHROME')
       string(name: 'TEST_PLAN_KEY')
    }

    triggers {
    cron('05 12 * * 1-5')
    }



    stages {
        stage('Export feature Xray') {
                steps {
                    script {

                        def token = bat(script: 'curl -s -X POST -H "Content-Type: application/json" -d "{\\"client_id\\":\\"93B37FB647824B09A6FD0C59815625CC\\",\\"client_secret\\":\\"6487d186161b48ea2906cb415dc22b14b4f1d16602b36eb8b9cf9b0dcd680d55\\"}" https://xray.cloud.getxray.app/api/v2/authenticate', returnStdout: true).trim().replace('"', '')

                        echo "Téléchargement des scénarios pour : ${params.XRAY_TEST_KEY}"

                        bat """
                               curl -H "Authorization: Bearer ${token}" "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=${params.XRAY_TEST_KEY}" --output features.zip

                               echo Extraction des fichiers .feature...

                               powershell Expand-Archive -Path features.zip -DestinationPath src/test/resources/features -Force
                           """
                        }
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