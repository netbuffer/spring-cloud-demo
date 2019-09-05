package cn.netbuffer.zuuldemo;

import cn.netbuffer.zuuldemo.fallback.MyFallbackProvider;
import cn.netbuffer.zuuldemo.filter.ZuulFilterDemo;
import cn.netbuffer.zuuldemo.filter.ZuulFilterDemo2;
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

    @Bean
    public ZuulFilterDemo2 buildZuulFilterDemo2() {
        return new ZuulFilterDemo2();
    }

    @Bean
    public MyFallbackProvider buildMyFallbackProvider() {
        return new MyFallbackProvider();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulDemoApplication.class, args);
    }

}
