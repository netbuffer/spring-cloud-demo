package cn.netbuffer.userserviceprovider2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2019/09/02
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    //https://github.com/spring-cloud-samples/config-repo/blob/master/application-dev.yml
    @Value("${my.prop}")
    private String prop;

    @GetMapping("prop")
    public String getProp() {
        return prop;
    }
}
