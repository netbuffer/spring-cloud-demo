# skywalking
* https://hub.docker.com/r/apache/skywalking-oap-server
* https://hub.docker.com/r/apache/skywalking-ui
* https://skywalking.apache.org/docs/main/v9.0.0/en/setup/backend/backend-setup/
* https://hub.docker.com/_/elasticsearch
* https://www.elastic.co/guide/en/elasticsearch/reference/7.5/docker.html

### reference
* docker-compose -f skywalking.yml -p skywalking up
* docker-compose -f skywalking.yml -p skywalking up -d
* docker-compose -f skywalking.yml -p skywalking stop skywalking-oap skywalking-ui
* docker-compose -f skywalking.yml -p skywalking start skywalking-oap skywalking-ui
* max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```
for windows
wsl -d docker-desktop
sysctl -w vm.max_map_count=262144

for linux
chmod 777 elasticsearch
chmod 777 config
chmod 777 data
chmod 777 logs

1. docker-compose -f skywalking.yml -p skywalking start elasticsearch
2. docker-compose -f skywalking.yml -p skywalking start skywalking-oap
3. docker-compose -f skywalking.yml -p skywalking start skywalking-ui
```