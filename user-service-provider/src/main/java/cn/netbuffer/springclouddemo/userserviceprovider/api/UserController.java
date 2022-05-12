package cn.netbuffer.springclouddemo.userserviceprovider.api;

import cn.netbuffer.springclouddemo.userserviceprovider.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable("id") Long id, Integer s) {
        log.info("receive getUser {},sleep {} s", id, s);
        if (s != null) {
            //模拟请求耗时测试熔断
            try {
                TimeUnit.SECONDS.sleep(s);
            } catch (InterruptedException e) {
                log.error("sleep error:{}", e.getMessage());
            }
        }
        String result = "user:" + id;
        log.info("return {}", result);
        return result;
    }

    @PostMapping
    public Map addUser(@RequestBody Map user) {
        log.info("receive user map={}", user);
        return userService.add(user);
    }
}