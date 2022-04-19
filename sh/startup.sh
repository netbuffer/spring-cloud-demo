#!/usr/bin/env bash

agent_path=your_agent_path
echo start eureka
java -Dskywalking.agent.service_name=scd::scd-eureka -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:$agent_path/skywalking-agent.jar -jar eureka-demo/target/eureka-demo.jar --spring.profiles.active=test &
echo start zuul
java -Dskywalking.agent.service_name=scd::scd-zuul -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:$agent_path/skywalking-agent.jar -jar zuul-demo/target/zuul-demo.jar --spring.profiles.active=test &
echo start usp
java -Dskywalking.agent.service_name=scd::scd-usp -Dskywalking.agent.instance_name=scd -Dskywalking.collector.backend_service=localhost:11800 -javaagent:$agent_path/skywalking-agent.jar -jar user-service-provider/target/user-service-provider.jar --spring.profiles.active=test &