node{
    stage('get clone'){
        //check CODE
       git credentialsId: '193d81cf-0102-4e27-93e4-19bf7689ab8d', url: 'https://github.com/ShallowDreamz/agent.git'
    }

    //����mvn����
    def mvnHome = tool 'maventest'
    env.PATH = "${mvnHome}/bin:${env.PATH}"

    stage('mvn test'){
        //mvn ����
        sh "mvn test"
    }

    stage('mvn build'){
        //mvn����
        sh "mvn clean install -Dmaven.test.skip=true"
    }

    stage('deploy'){
        //ִ�в���ű�
        echo "deploy ......" 
        rm -rf /opt/apache-tomcat-8.5.8/webapps/agent*
        mv ${WORKSPACE}/target/agent3.0-1.0-SNAPSHOT.war /opt/apache-tomcat-8.5.8/webapps/agent3.0.war
        pid=$(ps -ef | grep "apache-tomcat-8.5.8" | grep -v grep | awk '{print $2}')
        if [ -n "$pid" ]; then
        kill -9 $pid
        fi

        OLD_BUILD_ID=$BUILD_ID
        echo $OLD_BUILD_ID
        BUILD_ID=dontKillMe
        /opt/apache-tomcat-8.5.8/bin/startup.sh
        #�Ļ�ԭ����BUILD_IDֵ
        BUILD_ID=$OLD_BUILD_ID
        echo $BUILD_ID
    }
}