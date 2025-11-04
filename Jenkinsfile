pipeline {
    agent any // Run this pipeline on any available Jenkins agent

    tools {
        // These names MUST match what you set in Jenkins Global Tool Configuration
        maven 'Maven-3.9.0'
        jdk 'JDK-17'
    }

    stages {

        stage('2. Compile, Build & Unit Test') {
            steps {
                // This single command does it all:
                // 'clean': Deletes old builds
                // 'package': Compiles, runs unit tests, and packages into a .jar file
                sh 'mvn clean package'
            }
        }



        stage('4. Archive Artifact') {
            steps {
                // This saves the built .jar file inside Jenkins
                // so you can download it later
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('5. Deployment') {
            steps {
                echo 'Deploying application to port 8081...'

                // This is the permanent command to run in the background
                sh 'nohup java -jar -Dserver.port=8081 target/cicd-demo-0.0.1-SNAPSHOT.jar > app.log 2>&1 &'
            }
        }
    }

    post {
        // This block runs after all stages
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}