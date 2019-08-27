package cn.netbuffer.springclouddemo.userserviceinvoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserFallback")
    public String getUser(Long id) {
        return restTemplate.getForObject("http://user-service-provider/user/" + id, String.class);
    }

    public String getUserFallback(Long id) {
        return id + " error";
    }
}
