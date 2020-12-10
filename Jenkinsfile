
pipeline {
    agent any

    environment {
        jobCredentialsId = 'admin'
        iqStage = 'build'
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -Dproject.version=$BUILD_VERSION -Dmaven.test.failure.ignore clean install'
            }
        }

        stage('Nexus IQ Scan'){
            steps {
                script{
                    try {
                        def policyEvaluation = nexusPolicyEvaluation failBuildOnNetworkError: true, 
                                                                     iqApplication: selectedApplication('producer-ci'), 
                                                                     iqScanPatterns: [[scanPattern: '**/*.war']], 
                                                                     iqStage: "${iqstage}", 
                                                                     jobCredentialsId: "${jenkinsid}"

                        echo "Nexus IQ scan succeeded: ${policyEvaluation.applicationCompositionReportUrl}"
                    } 
                    catch (error) {
                        echo "Nexus IQ scan vulnerabilities detected', ${policyEvaluation.applicationCompositionReportUrl}"
                        throw error
                    }
                }
            }
        }
    }
}

