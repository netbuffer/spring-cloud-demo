package cn.netbuffer.springclouddemo.userserviceinvoker.controller;

import cn.netbuffer.springclouddemo.userserviceinvoker.client.UserClient;
import cn.netbuffer.springclouddemo.userserviceinvoker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/invoke")
public class UserController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserService userService;

    @Resource
    private UserClient userClient;

    @GetMapping("instances/{serviceId}")
    public List<ServiceInstance> getInstances(@PathVariable("serviceId") String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

    @GetMapping("discoveryclient")
    public DiscoveryClient getDiscoveryClient() {
        return discoveryClient;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") Long id, Integer s) {
        log.info("invoke get user {},sleep {} s", id, s);
        if (s == null) {
            return restTemplate.getForObject("http://user-service-provider/user/" + id, String.class);
        } else {
            try {
                TimeUnit.SECONDS.sleep(s);
            } catch (InterruptedException e) {
                log.error("sleep InterruptedException", e);
            }
            String result = "user:" + id;
            log.info("return {}", result);
            return result;
        }
    }

    @GetMapping("/hystrix/user/{id}")
    public String hgetUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/hystrix/user/wst/{id}")
    public String getUserWithSeparateThreadPool(@PathVariable("id") Long id) {
        return userService.getUserWithSeparateThreadPool(id);
    }

    @GetMapping("/feign/user/{id}")
    public String fgetUser(@PathVariable("id") Long id, @RequestParam(required = false) Integer s) {
        return userClient.getUser(id, s);
    }
}
