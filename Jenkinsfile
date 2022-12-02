pipeline {
    agent any
    tools {
      maven 'MAVEN 3.8.1'
      jdk 'JDK11'
    }

    stages {
        stage('Build') {
            steps {
                git credentialsId: 'git-credentials',
                branch: 'develop',
                    url: 'https://github.com/CCJose/super-heroes.git'
                sh "echo ${env.IS_SONAR}"
                sh "mvn clean install"
                sh "echo ${env.IS_SONAR}"
            }
        }
        stage('Sonarqube') {
            when {
                expression {
                    return env.IS_SONAR == true;
                }
            }
            steps {
                sh "mvn sonar:sonar -Dsonar.projectKey=primer-despliegue -Dsonar.host.url=http://172.20.0.1:9000 -Dsonar.login=f1e68a06a2f5152f822eba2edbc6594a8182d98a -Dsonar.qualitygate.wait=true"
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