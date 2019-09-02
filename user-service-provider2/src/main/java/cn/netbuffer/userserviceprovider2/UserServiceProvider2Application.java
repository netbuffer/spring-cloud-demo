package cn.netbuffer.userserviceprovider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class UserServiceProvider2Application {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceProvider2Application.class, args);
    }

}
