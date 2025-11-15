#!/usr/bin/env bash
# kill java applications : jcmd|awk '{print $1}'|xargs kill -9
scd_path=your_project_path

echo start eureka
nohup java -jar $scd_path/eureka-demo/target/eureka-demo.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start gateway
nohup java -jar $scd_path/spring-cloud-gateway-demo/target/spring-cloud-gateway-demo.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start usp
nohup java -jar $scd_path/user-service-provider/target/user-service-provider.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start usi
nohup java -jar $scd_path/user-service-invoker/target/user-service-invoker.jar --spring.profiles.active=test >/dev/null 2>&1 &
