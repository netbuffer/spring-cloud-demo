package cn.netbuffer.zuuldemo;

import cn.netbuffer.zuuldemo.filter.ZuulFilterDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ZuulDemoApplication {

    @Bean
    public ZuulFilterDemo buildZuulFilterDemo() {
        return new ZuulFilterDemo();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulDemoApplication.class, args);
    }

}
