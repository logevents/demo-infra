
private void createJob(java.lang.String jobName, projectUrl) {
    pipelineJob(jobName) {
        definition {
            cps {
                sandbox(true)
                script("""\
        pipeline {
            environment {
                def initEnableWarnings = 'allprojects {  gradle.projectsEvaluated { tasks.withType(JavaCompile) { options.compilerArgs << \"-Xlint:all\" } } }'
            }
            agent any
            stages {
                stage('Checkout') {
                    steps {
                        checkout([\$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: '$projectUrl']]])


                        writeFile file: "init.gradle", text: initEnableWarnings
                    }
                }
                stage('Build') {
                    steps {
                        sh "./gradlew -x test --init-script init.gradle --info --console=plain"
                    }
                }
                stage('Test') {
                    steps {
                        sh "./gradlew test --init-script init.gradle --info --console=plain"
                    }
                }
            }
        }
      """.stripIndent())
            }
        }
    }
}

createJob("spring-framework", "https://github.com/spring-projects/spring-framework.git")
createJob("spring-boot", "https://github.com/spring-projects/spring-boot.git")
createJob("spring-batch", "https://github.com/spring-projects/spring-batch.git")
createJob("junit-framework", "https://github.com/junit-team/junit5.git")

