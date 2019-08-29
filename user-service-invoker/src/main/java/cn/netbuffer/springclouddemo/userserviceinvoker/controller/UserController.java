package cn.netbuffer.springclouddemo.userserviceinvoker.controller;

import cn.netbuffer.springclouddemo.userserviceinvoker.client.UserClient;
import cn.netbuffer.springclouddemo.userserviceinvoker.service.UserService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

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
    public String getUser(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://user-service-provider/user/" + id, String.class);
    }

    @GetMapping("/hystrix/user/{id}")
    public String hgetUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/feign/user/{id}")
    public String fgetUser(@PathVariable("id") Long id, @RequestParam(required = false) Integer s) {
        return userClient.getUser(id, s);
    }
}
