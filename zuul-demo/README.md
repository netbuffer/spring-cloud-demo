* PatternServiceRouteMapper通过正则表达式自定义服务路径映射
![hystrix-command-flow-chart](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/hystrix-command-flow-chart.png)
当配置了MaxAutoRetries时候,实际上是执行MaxAutoRetries+1次
* http://localhost:8760/up/user/1?s=5
* http://localhost:8760/up/user/1?s=6 ,超过user-service-provider.ReadTimeout的值5s会熔断
* http://localhost:8760/ui/invoke/user/1?s=3
* http://localhost:8760/ui/invoke/user/1?s=4