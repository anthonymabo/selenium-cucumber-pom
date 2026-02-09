pipeline {
    agent any

    triggers {
    cron('55 11 * * 1-5')
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

        stage('Export to Jira') {
                    steps {
                        echo 'Envoi des résultats vers Jira Xray...'
                            bat 'curl -H "Content-Type: application/json" -X GET -H "Authorization: Bearer
                                 eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI1ZWU0ZjM3YjFmYjJjYTBhYzU0OTNhMjMiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTc3MDM4MzgxOSwiZXhwIjoxNzcwNDcwMjE5LCJhdWQiOiI5M0IzN0ZCNjQ3ODI0QjA5QTZGRDBDNTk4MTU2MjVDQyIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IjkzQjM3RkI2NDc4MjRCMDlBNkZEMEM1OTgxNTYyNUNDIn0.w5mcWGHM-k06DyjaBuWu34wX13i3rKDPWlmGjRckHb0"
                                 "https://xray.cloud.getxray.app/api/v1/export/cucumber?keys=POEI2-863"'
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