
node ('docker') {
	stage 'Compilation'
        stage 'Run maven docker image'
        def maven = docker.image('maven:latest')
        maven.inside('-v /tmp/docker-m2cache:/root/.m2:rw'){
                git branch: 'continuous-integration', credentialsId: 'jenkins_id', url: 'git@gitlab.redborder.lan:arodriguez/rb_cloudwatch.git'
        	sh 'mvn clean package'
              	stash includes: 'target/rb_cloudwatch*-selfcontained.jar', name: 'rb-cloudwatch'
        }	
}
node ('prep-manager') {
        stage 'Copy to preproduction'
	unstash 'rb-cloudwatch'
	stage 'Update service'
	sh 'rm -f /opt/rb/var/rb-cloudwatch/app/rb_cloudwatch*-selfcontained.jar'
	sh 'cp target/rb_cloudwatch*-selfcontained.jar /opt/rb/var/rb-cloudwatch/app/'
	sh '/opt/rb/bin/rb_manager_scp.sh all /opt/rb/var/rb-cloudwatch/app/rb_cloudwatch*-selfcontained.jar'
	sh '/opt/rb/bin/rb_service all restart rb-cloudwatch'
}
