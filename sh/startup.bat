@echo off
set SCD_PATH=%~dp0..\
echo start eureka
start java -jar "%SCD_PATH%eureka-demo\target\eureka-demo.jar" --spring.profiles.active=test
echo start gateway
start java -jar "%SCD_PATH%spring-cloud-gateway-demo\target\spring-cloud-gateway-demo.jar" --spring.profiles.active=test
echo start usp
start java -jar "%SCD_PATH%user-service-provider\target\user-service-provider.jar" --spring.profiles.active=test
echo start usi
start java -jar "%SCD_PATH%user-service-invoker\target\user-service-invoker.jar" --spring.profiles.active=test