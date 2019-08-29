package cn.netbuffer.springclouddemo.userserviceinvoker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service-provider")
public interface UserClient {

    @GetMapping("/user/{id}")
    String getUser(@PathVariable("id") Long id, @RequestParam Integer s);
}
