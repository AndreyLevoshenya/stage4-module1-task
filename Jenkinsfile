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
                sh './gradlew war'
                sh 'find . -type f -name "*.war"'
                deploy adapters: [tomcat9(credentialsId: 'adminDeploy', path: '', url: 'http://localhost:8888')],
                contextPath: 'webapps', war: 'build/libs/stage3-module4-task.war'
            }
        }
    }
}