package cn.netbuffer.springcloudconfigserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServerDemoApplication.class, args);
    }

}
