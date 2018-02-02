node {
   def mvnHome
   stage(Preparation) { // for display purposes
      // Get some code from a GitHub repository
      git https://github.com/jzingler/jsfBoot.git
      // Get the Maven tool.
      // ** NOTE: This M3 Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool M3
   }
   stage(Build) {
      // Run the maven build
      if (isUnix()) {
         sh "/bin/mvn -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage(Test) {
     steps {
        sh "/bin/mvn test -B
     }
     post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
     }
   }
}
