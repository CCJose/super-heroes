pipeline {
    agent any
    environment {
        jar_name = 'super-heroes-0.0.1-SNAPSHOT.jar'
        jar_path = ".//target//${jar_name}"
        bucket_url = 'https://primer-despliegue.s3.eu-west-1.amazonaws.com/'
    }
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
//                sh "mvn clean install"
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
        stage("Upload S3"){
                steps{
                    withAWS(region:"eu-west-1", credentials:"aws-credentials"){
                        s3Upload(file:"${jar_path}", bucket:"primer-despliegue", path:'')
                    }
                }
        }
        stage('Deploy') {
             steps {
               sshagent(credentials: ['ec2-key']) {
                    sh '''
                    [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                    ssh-keyscan -t rsa,dsa 54.217.20.46 >> ~/.ssh/known_hosts
                    ssh ec2-user@54.217.20.46 fuser -k 8081/tcp
                    ssh ec2-user@54.217.20.46 rm ${jar_name}
                    ssh ec2-user@54.217.20.46 wget ${bucket_url}${jar_name}
                    ssh ec2-user@54.217.20.46 java -jar ${jar_name} &
                    '''
                }
             }
        }
    }
}