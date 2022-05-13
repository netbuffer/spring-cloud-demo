### 关于zuul路由
* PatternServiceRouteMapper通过正则表达式自定义服务路径映射

### hystrix熔断处理流程
![hystrix-command-flow-chart](../help/hystrix-command-flow-chart.png)
* https://www.processon.com/view/5cde6469e4b0f0ee7b092044
* hystrix舱壁模式

### ribbon超时重试
ribbon当配置了MaxAutoRetries时候,实际上是执行MaxAutoRetries+1次接口调用,重试的时候可以指定响应码为多少时重试;
执行GET请求ConnectTimeout、ReadTimeout时会重试，post/put等操作默认是不会执行重试操作的

### test 
* http://localhost:8760/up/user/1?s=5
* http://localhost:8760/up/user/1?s=6 ,超过user-service-provider.ReadTimeout的值5s会熔断
* http://localhost:8760/ui/invoke/user/1?s=3
* http://localhost:8760/ui/invoke/user/1?s=4
* `wget https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/hystrix-command-flow-chart.png –no-check-certificate`
* http://localhost:8760/hystrix hystrix监控面板
* http://localhost:8760/actuator/hystrix.stream zuul hystrix监控数据源
* http://localhost:8760/turbine.stream turbine集群监控端点

### help
#### Hystrix Dashboard中的Thread pools项一直处于Loading状态
##### Hystrix 隔离策略配置
> zuul中Hystrix默认配置的隔离策略为SEMAPHORE,需要将Hystrix配置改为线程池的方式才能显示