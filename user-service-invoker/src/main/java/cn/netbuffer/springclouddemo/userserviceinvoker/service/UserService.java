package cn.netbuffer.springclouddemo.userserviceinvoker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class UserService {

    @Resource
    private RestTemplate restTemplate;

    /**
     * hystrix注解实例配置,配置熔断策略等
     *
     * @param id
     * @return
     */
    @CircuitBreaker(name = "userService", fallbackMethod = "getUserFallback")
    public String getUser(Long id) {
        String r = restTemplate.getForObject("http://user-service-provider/user/" + id, String.class);
        log.info("after getUser {},result:{}", id, r);
        return r;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "getUserFallback")
    public String getUserRandomSleep(Long id) {
        int sleep = RandomUtils.nextInt(6);
        log.info("invoke getUser {},sleep {} s", id, sleep);
        String r = restTemplate.getForObject("http://user-service-provider/user/" + id + "?s={1}", String.class, sleep);
        log.info("after getUser {},result:{}", id, r);
        return r;
    }

    /**
     * 线程池隔离配置（Hystrix样例）在 Resilience4j 中通过配置文件控制，这里直接保持业务逻辑不变。
     * @param id
     * @return
     */
    @CircuitBreaker(name = "userService", fallbackMethod = "getUserFallback")
    public String getUserWithSeparateThreadPool(Long id) {
        int sleep = RandomUtils.nextInt(6);
        log.info("invoke getUser {},sleep {} s", id, sleep);
        String r = restTemplate.getForObject("http://user-service-provider/user/" + id + "?s={1}", String.class, sleep);
        log.info("after getUser {},result:{}", id, r);
        return r;
    }

    public String getUserFallback(Long id, Throwable ex) {
        log.error("getUserFallback {}", id);
        return id + " error at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
