FROM javawiki/supervisord:v4.0.2-openjdk8
ADD */target/*.jar /usr/local/project/
ADD spring-cloud-demo.ini /etc/supervisor.d/spring-cloud-demo.conf