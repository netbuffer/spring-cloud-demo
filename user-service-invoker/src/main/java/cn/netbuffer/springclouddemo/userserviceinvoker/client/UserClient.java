package cn.netbuffer.springclouddemo.userserviceinvoker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * fallback参数
 */
@FeignClient(value = "user-service-provider", fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    String getUser(@PathVariable("id") Long id, @RequestParam Integer s);

    @Component
    class UserClientFallback implements UserClient {
        @Override
        public String getUser(Long id, Integer s) {
            return id + ":" + s + ":error at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

}
