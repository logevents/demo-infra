
private void createJob(java.lang.String jobName, projectUrl) {
    pipelineJob(jobName) {
        parameters {
            stringParam('checkoutdate', 'now', 'hh.mm.dddd  or now')
        }
        definition {
            cps {
                sandbox(true)
                script("""\
        pipeline {
            environment {
                def initEnableWarnings = 'allprojects {  tasks.withType(JavaCompile) { options.compilerArgs << "-Xlint:all" } }'
            }
            agent any
            stages {
                stage('Checkout') {
                    steps {
                        checkout([\$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: '$projectUrl']]])

                        
                        
                        if(!\$checkoutdate.equals("now")){
                            println("checkoutdate is \$checkoutdate")
                            sh 'git rev-list -1 --before="\\$checkoutdate" --date="format:dd.mm.yyyy" master'
                            sh 'git checkout `git rev-list -1 --before="\\$checkoutdate" --date="format:dd.mm.yyyy" master`'
                        }else{
                            println("checkoutdate (\$checkoutdate) is now")
                        }
                        
                        writeFile file: "init.gradle", text: initEnableWarnings
                    }
                }
                stage('Build') {
                    steps {
                        sh "./gradlew clean build -x test --init-script init.gradle --info"
                    }
                }
                stage('Test') {
                    steps {
                        sh "./gradlew clean test --init-script init.gradle --info"
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

