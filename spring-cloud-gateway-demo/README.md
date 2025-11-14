### Spring Cloud Gateway 路由
* PatternServiceRouteMapper通过正则表达式自定义服务路径映射

### hystrix熔断处理流程
![hystrix-command-flow-chart](../help/hystrix-command-flow-chart.png)
* https://www.processon.com/view/5cde6469e4b0f0ee7b092044
* hystrix舱壁模式

### ribbon超时重试
ribbon当配置了MaxAutoRetries时候,实际上是执行MaxAutoRetries+1次接口调用,重试的时候可以指定响应码为多少时重试;
执行GET请求ConnectTimeout、ReadTimeout时会重试，post/put等操作默认是不会执行重试操作的

### test 
* http://localhost:8760/gw/routes 查看当前Gateway路由
* http://localhost:8760/a/xxx 代理到百度
* http://localhost:8760/b/xxx 代理到 http://localhost:20000
* http://localhost:8760/up/user/1 经网关转发到 user-service-provider（StripPrefix=1）
* http://localhost:8760/ui/invoke/user/1 经网关转发到 user-service-invoker（StripPrefix=1）
* 任意请求 path 以 /deny 结尾将被全局过滤器拦截返回 401

### help
#### 说明
##### Gateway 相关
> 本模块基于 Spring Cloud Gateway 实现路由与过滤，不再使用 Zuul。若需熔断/限流，可选择 Resilience4j 或 Spring Cloud CircuitBreaker。