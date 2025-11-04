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
                echo 'DEBUG: Attempting to start application in foreground...'

                // We will run the app for 15 seconds to see its log.
                // The build will be "Aborted" by the timeout, which is OK.
                // We just want to read the console output from this stage.
                timeout(time: 15, unit: 'SECONDS') {
                    sh 'java -jar -Dserver.port=8081 target/cicd-demo-0.0.1-SNAPSHOT.jar'
                }
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