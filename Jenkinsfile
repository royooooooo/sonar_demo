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
                sh "./gradlew test jacocoTestReport --info"
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'build/reports/**'
        }
    }
}