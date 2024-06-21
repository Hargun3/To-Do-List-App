pipeline {
    agent any
    
    environment {
        ANDROID_HOME = "${tool 'Android SDK'}" // Adjust as per your Jenkins tool configuration
        PATH = "${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${env.PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/Hargun3/To-Do-List-App.git']]])
            }
        }
        
        stage('Build') {
            steps {
                sh './gradlew assembleDebug' // Adjust Gradle task as per your project
            }
        }
        
        stage('Test') {
            steps {
                sh './gradlew testDebugUnitTest' // Adjust Gradle task as per your project
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying the application...' // Adjust deployment steps as per your deployment process
            }
        }
    }
    
    post {
        success {
            echo 'Build successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
