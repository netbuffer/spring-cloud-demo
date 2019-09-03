package cn.netbuffer.userserviceprovider2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RefreshScope配置信息刷新能力
 * @author admin
 * @date 2019/09/02
 */
@RefreshScope
@RestController
@RequestMapping("/profile")
public class ProfileController {

    //https://github.com/netbuffer/spring-cloud-config-repo-demo/blob/master/user-service-provider2-dev.yml
    @Value("${spring.application.name}")
    private String prop;

    @GetMapping("prop")
    public String getProp() {
        return prop;
    }
}
