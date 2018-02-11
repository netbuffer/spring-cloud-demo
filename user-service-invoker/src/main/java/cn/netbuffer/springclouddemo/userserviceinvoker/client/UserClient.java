package cn.netbuffer.springclouddemo.userserviceinvoker.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/invoke")
public class UserClient {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://user-service-provider/user/" + id, String.class);
    }
}
