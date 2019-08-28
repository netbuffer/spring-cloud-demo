package cn.netbuffer.springclouddemo.userserviceinvoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {

    @Resource
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserFallback")
    public String getUser(Long id) {
        int sleep = RandomUtils.nextInt(6);
        log.info("invoke getUser {},sleep {} s", id, sleep);
        //模拟请求耗时测试熔断
        try {
            TimeUnit.SECONDS.sleep(sleep);
        } catch (InterruptedException e) {
            log.error("sleep error:{}", e.getMessage());
        }
        String r = restTemplate.getForObject("http://user-service-provider/user/" + id, String.class);
        log.info("after getUser {},result:{}", id, r);
        return r;
    }

    public String getUserFallback(Long id) {
        log.error("getUserFallback {}", id);
        return id + " error at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
