package cn.netbuffer.springclouddemo.userserviceinvoker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

/**
 * fallback参数
 */
@FeignClient(value = "user-service-provider", fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    Map<String, Object> nullUser = Map.of("id", -1);

    @GetMapping("/user/{id}")
    String getUser(@PathVariable("id") Long id, @RequestParam Integer s);

    @PostMapping("/user")
    Map<String, Object> add(@RequestBody Map<String, Object> user);

    @Component
    @Slf4j
    class UserClientFallback implements UserClient {

        private final Counter getUserFallbackCounter;
        private final Counter addFallbackCounter;

        @Autowired
        public UserClientFallback(MeterRegistry registry) {
            this.getUserFallbackCounter = Counter.builder("feign_fallback_total")
                    .tag("client", "user-service-provider")
                    .tag("method", "getUser")
                    .description("Total number of feign fallbacks for getUser")
                    .register(registry);
            this.addFallbackCounter = Counter.builder("feign_fallback_total")
                    .tag("client", "user-service-provider")
                    .tag("method", "add")
                    .description("Total number of feign fallbacks for add")
                    .register(registry);
        }
        @Override
        public String getUser(Long id, Integer s) {
            getUserFallbackCounter.increment();
            log.warn("feign fallback: client=user-service-provider method=getUser id={} s={} at={}", id, s,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return id + ":" + s + ":error at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        @Override
        public Map<String, Object> add(Map<String, Object> user) {
            addFallbackCounter.increment();
            log.warn("feign fallback: client=user-service-provider method=add payloadKeys={} at={}",
                    user != null ? user.keySet() : null,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return nullUser;
        }
    }
}
