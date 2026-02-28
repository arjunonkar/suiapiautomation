pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test -Dheadless=true'
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure includeProperties: false,
                       results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**', allowEmptyArchive: true
        }
    }
}