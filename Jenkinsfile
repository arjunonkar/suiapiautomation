pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn test -Dcucumber.filter.tags="@API"'
            }
        }

        stage('Run UI Tests') {
            steps {
                bat 'mvn test -Dcucumber.filter.tags="@UI"'
            }
        }
    }

    post {
        always {
            allure includeProperties: false,
                   jdk: '',
                   results: [[path: 'target/allure-results']]
        }
    }
}