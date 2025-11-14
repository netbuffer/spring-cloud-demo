# spring-cloud-demo
![](https://img.shields.io/static/v1?label=java&message=21&color=blue)
![](https://img.shields.io/static/v1?label=maven&message=3.9.6&color=blue)
![](https://img.shields.io/static/v1?label=spring-boot&message=3.4.5&color=blue)
![](https://img.shields.io/static/v1?label=spring-cloud&message=2024.0.2&color=brightgreen)
![](https://img.shields.io/static/v1?label=lombok&message=1.18.34&color=blue)

* https://github.com/netbuffer/spring-cloud-demo  
* https://gitee.com/netbuffer/spring-cloud-demo

### help
![spring-cloud](help/spring-cloud.png)
* [Spring Cloud 2024.0.x Reference](https://docs.spring.io/spring-cloud-release/reference/2024.0/index.html)
* [Spring Cloud Gateway](https://docs.spring.io/spring-cloud-gateway/docs/4.1.2/reference/html/)
* [Spring Cloud LoadBalancer](https://docs.spring.io/spring-cloud-commons/docs/4.2.2/reference/html/#spring-cloud-loadbalancer)
* [Spring Cloud OpenFeign](https://docs.spring.io/spring-cloud-openfeign/docs/4.2.2/reference/html/)
* [Resilience4j](https://resilience4j.readme.io/docs)
* [Spring Cloud Config](https://docs.spring.io/spring-cloud-config/docs/4.2.0/reference/html/)

### Docker Image
* https://hub.docker.com/r/javawiki/spring-cloud-demo
* docker build -t javawiki/spring-cloud-demo:v1.0.0 .
* docker run --rm -it -p 8761:8761 -p 8701:8701 -p 8700:8700 -p 8760:8760 -e TZ=Asia/Shanghai --name scd -h scd javawiki/spring-cloud-demo:v1.0.0 bash

### reference
* java -Dskywalking.agent.service_name=scd::scd-eureka -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:your_absolute_path/skywalking-agent.jar -jar eureka-demo/target/eureka-demo.jar --spring.profiles.active=test
* java -Dskywalking.agent.service_name=scd::scd-gateway -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:your_absolute_path/skywalking-agent.jar -jar spring-cloud-gateway-demo/target/spring-cloud-gateway-demo.jar --spring.profiles.active=test
* java -Dskywalking.agent.service_name=scd::scd-usp -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:your_absolute_path/skywalking-agent.jar -jar user-service-provider/target/user-service-provider.jar --spring.profiles.active=test