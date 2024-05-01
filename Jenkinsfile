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
                deploy adapters: [tomcat8(credentialsId: 'tomcat', path: '', url: 'http://192.168.100.13:8088')], contextPath: './app/', war: '**/*.war'
            }
        }
    }
}