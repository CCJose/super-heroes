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
                sh "mvn clean install"
            }
        }
        stage('Sonar') {
            when {
               environment name: 'IS_SONAR', value: "true"
            }
            steps {
                sh "mvn sonar:sonar -Dsonar.projectKey=primer-despliegue -Dsonar.host.url=http://172.20.0.1:9000 -Dsonar.login=f1e68a06a2f5152f822eba2edbc6594a8182d98a -Dsonar.qualitygate.wait=true"
            }
        }
        stage('Deploy') {
            steps {
                sshagent(credentials: ['ec2-key']) {
                    sh '''
                    [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                    ssh-keyscan -t rsa,dsa http:localhost:8080 >> ~/.ssh/known_hosts
                    ssh ec2-user@44.202.9.94 ls
                    '''
                }
            }
        }
    }
}