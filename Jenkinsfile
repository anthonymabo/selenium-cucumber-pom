pipeline {
    agent any
    parameters {
       string(name: 'SELENIUM_BROWSER', defaultValue:'CHROME')
       string(name: 'TEST_PLAN_KEY', defaultValue: 'POEI2-654')
    }

    triggers { cron('10 00 * * 1-5') }


    environment {
        TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MTIwMjA6MGVkNjY4NzAtM2Y2ZC00NzM3LTljMmYtZDQyMTVmM2MyOWRmIiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3NzA3MjQ1MzYsImV4cCI6MTc3MDgxMDkzNiwiYXVkIjoiNTY3NTJEQUJBMENDNERBQ0IzQUQ1RTcyMEZGRDJDN0UiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiI1Njc1MkRBQkEwQ0M0REFDQjNBRDVFNzIwRkZEMkM3RSJ9.0No-A72amxzhPrMgNAEN8kQCYBW8PNi-SSmVZIMmb08"
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