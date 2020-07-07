package cn.netbuffer.userserviceprovider2;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringCloudApplication
public class UserServiceProvider2Application {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceProvider2Application.class, args);
    }

}
