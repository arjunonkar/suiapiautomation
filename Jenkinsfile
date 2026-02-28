pipeline {
    agent any

    stages {
        stage('API Tests') {
            steps {
                bat 'mvn clean test -Dcucumber.filter.tags="@API"'
            }
        }

        stage('UI Tests') {
            steps {
                bat 'mvn clean test -Dcucumber.filter.tags="@UI" -Dheadless=true'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}