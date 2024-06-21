pipeline {
    agent any
    
    environment {
        ANDROID_HOME = tool 'Android SDK' // Make sure 'Android SDK' matches the name you specified in Jenkins Global Tool Configuration
        PATH = "${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${env.PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/Hargun3/To-Do-List-App.git']]])
            }
        }
        
        stage('Build') {
            steps {
                sh './gradlew assembleDebug'
            }
        }
        
        stage('Test') {
            steps {
                sh './gradlew testDebugUnitTest'
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deployment steps...'
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
