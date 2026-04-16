pipeline {
    agent any

    tools {
        maven 'Maven-3'
    }

    stages {

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