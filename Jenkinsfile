pipeline {

    agent any

    tools {
        maven 'Maven-3'
    }

    stages {

        stage('Checkout Code') {

            steps {
                git 'https://github.com/VikramDharman30/Fintech-API-Automation.git'
            }
        }

        stage('Build & Test') {

            steps {
                bat 'mvn clean test'
            }
        }

        stage('Archive Report') {

            steps {
                archiveArtifacts artifacts: 'test-output/**', fingerprint: true
            }
        }
    }
}