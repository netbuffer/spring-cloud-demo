package cn.netbuffer.zuuldemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Value("${hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds:null}")
    private String hystrixTimeout;

    @Resource
    private Environment environment;

    @GetMapping("hystrixTimeout")
    public String getHystrixTimeout() {
        return hystrixTimeout;
    }

    @GetMapping("environment/property")
    public String environment(String property) {
        return environment.getProperty(property);
    }

}
