pipeline {
    agent {
        node {
            label 'docker-zd-agent'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                withMaven(maven 'Maven-3-9') {
                    sh '''
                        mvn clean install
                    '''
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
