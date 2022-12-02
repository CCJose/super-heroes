pipeline {
    agent any
    tools {
      maven 'MAVEN 3.8.1'
      jdk 'JDK11'
    }

    stages {
        stage('Build') {
            steps {
                git branch: 'develop',
                    url: 'https://github.com/CCJose/super-heroes.git'

                sh "mvn clean"
                sh "mvn install"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}