> https://zipkin.io/pages/quickstart  
https://hub.docker.com/r/openzipkin/zipkin/
### java
* java -jar zipkin-server-2.16.2-exec.jar --server.port=8900

### docker
* docker run -d -p 8900:9411 openzipkin/zipkin:2

### docker compose
* docker-compose -f zipkin-server.yaml up -d