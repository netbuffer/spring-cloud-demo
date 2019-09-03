package cn.netbuffer.userserviceprovider2.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author admin
 * @RefreshScope配置信息刷新能力
 * @date 2019/09/02
 */
@RefreshScope
@RestController
@RequestMapping("/profile")
public class ProfileController {

    //https://github.com/netbuffer/spring-cloud-config-repo-demo/blob/master/user-service-provider2-dev.yml
    @Resource
    private Environment environment;

    @GetMapping("env/{prop}")
    public String getProp(@PathVariable String prop) {
        return environment.getProperty(prop);
    }

    @GetMapping("active")
    public String[] active() {
        return environment.getActiveProfiles();
    }

    @GetMapping("default")
    public String[] defaultProfile() {
        return environment.getDefaultProfiles();
    }
}
