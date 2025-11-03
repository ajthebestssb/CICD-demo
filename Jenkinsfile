pipeline {
    agent any // Run this pipeline on any available Jenkins agent

    tools {
        // These names MUST match what you set in Jenkins Global Tool Configuration
        maven 'Maven-3.9.0'
        jdk 'JDK-17'
    }

    stages {
        stage('1. Git Code Checkout') {
            steps {
                // Clones your repository
                git 'https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git'
            }
        }

        stage('2. Compile, Build & Unit Test') {
            steps {
                // This single command does it all:
                // 'clean': Deletes old builds
                // 'package': Compiles, runs unit tests, and packages into a .jar file
                sh 'mvn clean package'
            }
        }

        stage('3. SonarQube Analysis (Optional)') {
            // This stage will only run if you configure SonarQube in Jenkins
            steps {
                // 'MySonarQubeServer' must match the name in Jenkins configuration
                withSonarQubeEnv('MySonarQubeServer') {

                    // 'verify' runs tests again, 'sonar:sonar' sends the report//
                    sh 'mvn verify sonar:sonar'
                }
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
                echo 'Deploying application...'
                // This command runs your Java application in the background
                // 'nohup' means "no hang up" (it keeps running after the job ends)
                // '... > app.log 2>&1 &' redirects all output to a file named 'app.log'
                sh 'nohup java -jar target/cicd-demo-0.0.1-SNAPSHOT.jar > app.log 2>&1 &'
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