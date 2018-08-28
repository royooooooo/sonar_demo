pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "./gradlew clean compileJava compileTestJava --info"
            }
        }

        stage('Test') {
            steps {
                sh "./gradlew test --info"
            }
        }

        stage('SonarQube analysis') {
            steps{
                withSonarQubeEnv('My SonarQube Server') {
                   sh './gradlew --info sonarqube'
                }
            }
        }

    }
}

