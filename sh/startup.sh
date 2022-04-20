#!/usr/bin/env bash

agent_path=your_agent_path
echo start eureka
nohup java -Dskywalking.agent.service_name=scd::scd-eureka -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:$agent_path/skywalking-agent.jar -jar eureka-demo/target/eureka-demo.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start zuul
nohup java -Dskywalking.agent.service_name=scd::scd-zuul -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:$agent_path/skywalking-agent.jar -jar zuul-demo/target/zuul-demo.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start usp
nohup java -Dskywalking.agent.service_name=scd::scd-usp -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:$agent_path/skywalking-agent.jar -jar user-service-provider/target/user-service-provider.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start usi
nohup java -Dskywalking.agent.service_name=scd::scd-usi -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:$agent_path/skywalking-agent.jar -jar user-service-invoker/target/user-service-invoker.jar --spring.profiles.active=test >/dev/null 2>&1 &