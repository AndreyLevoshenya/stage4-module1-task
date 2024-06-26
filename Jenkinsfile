pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew jacocoTestReport'
            }
        }
        stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube') {
                    sh './gradlew sonar'
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy') {
            steps {
                sh './gradlew bootWar'
                sh 'find . -type f -name "*.war"'
                deploy adapters: [tomcat9(credentialsId: 'adminDeploy', path: '', url: 'http://192.168.1.6:8888')],
                contextPath: 'app', war: 'module-web/build/libs/application-1.0.0.war'
            }
        }
    }
}