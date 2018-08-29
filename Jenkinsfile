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
               sh './gradlew sonarqube'
            }
        }

        stage('Quality Gate') {
            steps {
                echo "starting codeAnalyze with SonarQube......"
                //sonar:sonar.QualityGate should pass
                withSonarQubeEnv('SonarQube') {
                    //固定使用项目根目录${basedir}下的pom.xml进行代码检查
                    sh "mvn -f pom.xml clean compile sonar:sonar"
                }
                script {
                    timeout(10) {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "未通过Sonarqube的代码质量阈检查，请及时修改！failure: ${qg.status}"
                        }
                    }
                }
            }
        }
    }
}