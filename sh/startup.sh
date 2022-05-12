#!/usr/bin/env bash
# kill java applications : jcmd|awk '{print $1}'|xargs kill -9
agent_path=your_agent_path
scd_path=your_project_path

service_name_prefix=-Dskywalking.agent.service_name=scd::
instance_name_param=-Dskywalking.agent.instance_name=scd
backend_service_param=-Dskywalking.collector.backend_service=localhost:11800
extra_param="-Dskywalking.trace.ignore_path=/actuator/**,/ -Dskywalking.plugin.jdbc.trace_sql_parameters=true -Dskywalking.plugin.springmvc.collect_http_params=true -Dskywalking.plugin.httpclient.collect_http_params=true"
javaagent_param=-javaagent:$agent_path/skywalking-agent.jar
common_param="$instance_name_param $backend_service_param $extra_param $javaagent_param"

echo start eureka
nohup java ${service_name_prefix}scd-eureka $common_param -jar $scd_path/eureka-demo/target/eureka-demo.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start zuul
nohup java ${service_name_prefix}scd-zuul $common_param -jar $scd_path/zuul-demo/target/zuul-demo.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start usp
nohup java ${service_name_prefix}scd-usp $common_param -jar $scd_path/user-service-provider/target/user-service-provider.jar --spring.profiles.active=test >/dev/null 2>&1 &
echo start usi
nohup java ${service_name_prefix}scd-usi $common_param -jar $scd_path/user-service-invoker/target/user-service-invoker.jar --spring.profiles.active=test >/dev/null 2>&1 &
