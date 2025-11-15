# 平台与基础
- **Java 版本门槛**
    - Greenwich 基于 Spring Boot 2.1/2.2，支持 Java 8。
    - 2024.0.x 基于 Spring Boot 3.2+/3.4，需要 **Java 17+**。
- **Jakarta 命名空间迁移**
    - 从 javax.* 迁到 jakarta.*（来源于 Spring Framework 6/Boot 3 的重大变更）。
    - 涉及所有 Servlet/JPA/Validation 等生态接口。

# 依赖与生态替换（Netflix OSS 退出）
- **Zuul → Spring Cloud Gateway**
    - Zuul 1 已被弃用/移除；官方主推 Gateway（基于 WebFlux，响应式，性能更好，功能更全）。
- **Ribbon → Spring Cloud LoadBalancer**
    - 客户端负载均衡改为 LoadBalancer，配置项与重试模型不同。
- **Hystrix → Resilience4j / Spring Cloud CircuitBreaker**
    - 舱壁/限流/熔断改用 Resilience4j 或抽象的 CircuitBreaker，API 与配置变化较大。
- **Sleuth → Micrometer Tracing / OpenTelemetry**
    - 链路追踪由 Sleuth 迁到 Micrometer Tracing（可对接 Brave/OTel），配置/埋点思路调整。
- 其他弃用：Archaius、Turbine 等 Netflix 组件。

# 架构与编程模型
- **Servlet（MVC）与 WebFlux 分层更清晰**
    - Gateway 必须运行在响应式栈（WebFlux），和 Spring MVC 不兼容。
    - 混用会报错，需要 `spring.main.web-application-type=reactive` 或拆分模块。
- **测试框架**
    - 默认 **JUnit 5**（Jupiter），JUnit4 注解与 Runner 需迁移。

# 配置体系变化
- **bootstrap.yml 调整**
    - Boot 2.4+ 引入新配置加载机制。推荐使用 `spring.config.import=configserver:`。
    - 如仍依赖旧时序，可加 `spring-cloud-starter-bootstrap` 恢复 bootstrap 阶段。
- **Actuator 与管理端口**
    - 端点名与暴露策略有调整（需核对文档）。

# OpenFeign 与 HTTP 客户端
- **OpenFeign 4.x** 与 Spring Cloud OpenFeign 适配
    - 客户端与超时、重试策略更偏向 LoadBalancer/Resilience4j 组合。
    - 某些默认 HTTP 客户端实现（Apache HC5/OkHttp）与配置项名字可能不同。

# 安全与依赖约束
- **Spring Security 6**（若涉及）
    - 配置 DSL 变化较大（authorizeHttpRequests 等）。
- **依赖版本对齐**
    - 尽量通过 Spring Cloud 依赖管理对齐版本，避免“类找不到/方法签名变化”。

# 观测性与日志
- **Micrometer 统一度量**
    - 指标、日志、追踪的 Micrometer 一体化。
- **SkyWalking 集成方式**
    - 建议通过 Micrometer Tracing + OTel exporter 或官方探针，不再建议代码里耦合特定日志 appender。

# 学习与迁移成本评估
- **低-中**：
    - Eureka 基本用法变化不大。
    - Config Server/Client 主要是加载顺序与 bootstrap 改动。
    - JUnit5 迁移简单（替换注解与 Runner）。
- **中-高**：
    - Zuul → Gateway（响应式编程模型、过滤器/谓词、路由配置新语义）。
    - Ribbon/Hystrix → LoadBalancer/Resilience4j（重试、超时、熔断与隔离策略的“观念更新”）。
    - Jakarta 迁移（如果你有大量依赖 javax 接口的自定义代码）。
- 预估时间（经验值）：
    - 小型项目：1~2 天掌握与迁移主要路径（不含复杂业务改造）。
    - 中等规模：3~5 天覆盖网关、熔断/重试、配置加载、观测性链路。

# 迁移清单（实操导向）
- **基础与构建**
    - 升级到 JDK 17+、Boot 3.4.x、Spring Cloud 2024.0.x。
    - JUnit5、Maven 插件与编译参数统一（release 17/21）。
- **网关**
    - 移除 Zuul，改用 Gateway。
    - 确保项目没有引入 spring-boot-starter-web 的运行时依赖到 Gateway。
- **负载与容错**
    - 移除 Ribbon/Hystrix；引入 LoadBalancer 与 Resilience4j/CircuitBreaker。
    - 重写重试/超时/熔断配置。
- **配置中心**
    - 优先用 `spring.config.import=configserver:`；必要时引入 bootstrap starter。
- **追踪与日志**
    - 迁移到 Micrometer Tracing（可接 Brave/OTel）；日志使用标准 appender，避免强绑定特定 APM 插件。
- **测试**
    - 全量替换 JUnit4 注解/Runner 为 JUnit5。
