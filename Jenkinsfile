pipeline {
    agent any
    tools {
      maven 'MAVEN_HOME'
      jdk 'JAVA_HOME'
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/CCJose/super-heroes.git'

                sh "mvn clean package"
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