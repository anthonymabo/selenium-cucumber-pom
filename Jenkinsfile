pipeline {
    agent any
    parameters {
       string(name: 'SELENIUM_BROWSER', defaultValue:'CHROME')
       string(name: 'TEST_PLAN_KEY', defaultValue: 'POEI2-654')
    }
    triggers { cron('05 12 * * 1-5') }

    stages {
        stage('Xray Import') {
            steps {
                script {
                    // 1. Récupération du token (avec @echo off pour éviter les bruits de console Windows)
                    def token = bat(script: '@echo off \n curl -s -X POST -H "Content-Type: application/json" -d "{\\"client_id\\":\\"93B37FB647824B09A6FD0C59815625CC\\",\\"client_secret\\":\\"6487d186161b48ea2906cb415dc22b14b4f1d16602b36eb8b9cf9b0dcd680d55\\"}" https://xray.cloud.getxray.app/api/v2/authenticate', returnStdout: true).trim().replaceAll('"', '')

                    // 2. Téléchargement et Unzip combinés
                    bat """
                        curl -H "Authorization: Bearer ${token}" "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=${params.TEST_PLAN_KEY}" -o f.zip
                        powershell Expand-Archive -Path f.zip -DestinationPath src/test/resources/features -Force
                    """
                }
            }
        }

        stage('Build & Test') {
            steps {
                echo "Tests sur ${params.SELENIUM_BROWSER}..."
                bat 'mvn test'
            }
        }
    }

    post {
        always { bat 'del f.zip' } // Nettoyage propre
        success { echo 'Succès 🎉' }
        failure { echo 'Échec ❌' }
    }
}