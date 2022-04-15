* https://hub.docker.com/_/elasticsearch
* docker-compose -f skywalking.yml -p skywalking up
### reference
* max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```
wsl -d docker-desktop
sysctl -w vm.max_map_count=262144
1. docker-compose -f skywalking.yml -p skywalking start elasticsearch
2. docker-compose -f skywalking.yml -p skywalking start skywalking-oap
3. docker-compose -f skywalking.yml -p skywalking start skywalking-ui
```