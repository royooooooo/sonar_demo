pipeline {
    agent {
        label 'java'
    }
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
    node{
        stage("Quality Gate") {
          timeout(time: 1, unit: 'HOURS') {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
              error "Pipeline aborted due to quality gate failure: ${qg.status}"
           }
         }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'build/reports/**'
        }
    }
}