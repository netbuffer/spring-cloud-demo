### Spring Cloud Gateway 路由
* PatternServiceRouteMapper通过正则表达式自定义服务路径映射

### hystrix熔断处理流程
![hystrix-command-flow-chart](../help/hystrix-command-flow-chart.png)
* https://www.processon.com/view/5cde6469e4b0f0ee7b092044
* hystrix舱壁模式

### 负载与容错（2024.x）
使用 Spring Cloud LoadBalancer + Resilience4j 取代 Ribbon/Hystrix：
* 超时：在 Feign 中配置 `connectTimeout`、`readTimeout`
* 负载：`spring.cloud.loadbalancer.*`（如缓存 TTL）
* 熔断/重试/超时保护：`resilience4j.circuitbreaker|retry|timelimiter`

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