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

        stage("Quality Gate") {
            steps{
              timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
                def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
                }
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