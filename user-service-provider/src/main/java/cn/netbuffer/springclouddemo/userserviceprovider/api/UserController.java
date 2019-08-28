package cn.netbuffer.springclouddemo.userserviceprovider.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

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
        return "user:" + id;
    }
}