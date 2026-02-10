pipeline {
    agent any
    parameters {
       string(name: 'SELENIUM_BROWSER', defaultValue:'CHROME')
       string(name: 'TEST_PLAN_KEY', defaultValue: 'POEI2-654')
    }

    triggers { cron('10 00 * * 1-5') }


    environment {
        TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI1ZWU0ZjM3YjFmYjJjYTBhYzU0OTNhMjMiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTc3MDcxNzczOCwiZXhwIjoxNzcwODA0MTM4LCJhdWQiOiI5M0IzN0ZCNjQ3ODI0QjA5QTZGRDBDNTk4MTU2MjVDQyIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IjkzQjM3RkI2NDc4MjRCMDlBNkZEMEM1OTgxNTYyNUNDIn0.gzAUljdKOVolULBm9uFe0KXoakvQuDl8G_hJJesq6Cc"
    }

    stages {
        stage('Export features') {
            steps {
                echo "Exportation des features pour : ${params.TEST_PLAN_KEY}"

                bat 'curl -f -H "Authorization: Bearer %TOKEN%" "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=%TEST_PLAN_KEY%" --output features.zip'

                bat 'if not exist "src/test/resources/features" mkdir "src/test/resources/features"'
                bat 'tar -xf features.zip -C src/test/resources/features'
                bat 'del features.zip'
            }
        }

        stage('Build & Test') {
             steps {
                echo 'Execution des tests Cucumber via Maven...'
                bat 'mvn clean test'
             }
        }
    }

    post {
        success { echo 'Tests exécutés avec succès 🎉' }
        failure { echo 'Des tests ont échoué ❌' }
    }
}