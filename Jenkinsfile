pipeline {
  agent any 
  tools {
    maven 'Maven'
  }
  stages {
    stage ('Initialize') {
      steps {
        sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
            ''' 
      }
    }
    stage ('Check-Git-Secrets') {
	steps {
		sh 'rm trufflelog || true'
		sh 'docker run gesellix/trufflehog --json --regex https://github.com/save2017/DevSecOps-SOC.git > trufflehog'
		sh 'cat trufflehog'
		}
			}
    
    stage('Source-Composition-analysis'){
	steps{
		sh 'mvn clean'
		sh 'rm owasp* || true'
		sh 'wget "https://raw.githubusercontent.com/save2017/DevSecOps-SOC/master/owasp-dependency-check.sh" '
		sh 'chmod +x owasp-dependency-check.sh'
		sh 'bash owasp-dependency-check.sh'
		}
	  }
	  
	  
    stage ('Build'){
      steps {
     sh 'mvn clean package'
    }
    } 
    
    stage ('Deploy-To-Tomcat') {
            steps {
           sshagent(['tomcat']) {
                sh 'scp -o StrictHostKeyChecking=no target/*.war root@192.168.127.193:/prod/apache-tomcat-8.5.54/webapps/DevSecOps-SOC.war'
	     }      
           }       
     }
	  
     stage ('Deploy-To-Kubernetes') {
            steps {
	      sshagent(['ZAP']) {
                   sh 'scp -o StrictHostKeyChecking=no *.tar root@192.168.127.227:/'
		 }
            }
       }   
         
  }
}
