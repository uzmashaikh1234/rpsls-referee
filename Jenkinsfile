pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.12.101-hotspot'
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    options {
        timeout(time: 5, unit: 'MINUTES')
    }

    parameters {
        choice(name: 'P1', choices: ['rock', 'paper', 'scissors', 'lizard', 'spock'], description: 'Player 1 move')
        choice(name: 'P2', choices: ['spock', 'lizard', 'scissors', 'paper', 'rock'], description: 'Player 2 move')
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the referee...'
                bat 'javac Referee.java RefereeTest.java'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing all 25 move combinations...'
                bat 'java RefereeTest'
            }
        }

        stage('Play') {
            steps {
                echo "Referee judging: ${params.P1} vs ${params.P2}"
                bat "java Referee ${params.P1} ${params.P2} > result.txt"
                bat 'type result.txt'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'result.txt', allowEmptyArchive: true
            echo 'The referee has left the arena.'
        }
        success {
            echo 'BUILD SUCCESSFUL - Spock approves!'
        }
        failure {
            echo 'BUILD FAILED - a rule is broken. Check the Test stage.'
        }
    }
}
