package cn.netbuffer.springclouddemo.userserviceinvoker.client;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/invoke")
public class UserClient {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

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
}
