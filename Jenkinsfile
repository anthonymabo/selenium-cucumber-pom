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

        stage('Export from Jira') {
           def clientId = "93B37FB647824B09A6FD0C59815625CC"
           def clientSecret = "6487d186161b48ea2906cb415dc22b14b4f1d16602b36eb8b9cf9b0dcd680d55"
           def testKey = "POEI20252-654"
                            steps {
                                echo 'creation du token...'
                                  def token = bat(script: """
                                                          curl -s -H "Content-Type: application/json" -X POST ^
                                                          -d "{\\"client_id\\": \\"${clientId}\\", \\"client_secret\\": \\"${clientSecret}\\"}" ^
                                                          https://xray.cloud.getxray.app/api/v2/authenticate
                                                      """, returnStdout: true).trim().replaceAll('"', '')

                                echo 'download test...'
                                  bat """
                                                          curl -H "Authorization: Bearer ${token}" ^
                                                          "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=${testKey}" ^
                                                          -o src/test/resources/features/xray_features.zip
                                                          """

                                echo 'unzip fichier...'
                                   bat """ powershell Expand-Archive -Path Téléchargements
                                                          -DestinationPath C:\Dev\tp sauce demo\selenium-cucumber-pom\src\test\resources\features
                                       """

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