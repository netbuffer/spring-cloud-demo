package cn.netbuffer.springclouddemo.userserviceinvoker;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableCircuitBreaker hystrix断路器
 * @EnableFeignClients feign声明式接口调用
 * @SpringCloudApplication已经集成@SpringBootApplication/@EnableDiscoveryClient/@EnableCircuitBreaker
 */
@EnableFeignClients
@SpringCloudApplication
public class UserServiceInvokerApplication {

    /**
     * ribbon客户端负载均衡实例
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceInvokerApplication.class, args);
    }
}
