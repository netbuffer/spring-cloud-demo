package cn.netbuffer.zuuldemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/z")
public class ZuulRouteController {

    @Resource
    private ZuulProperties zuulProperties;

    @GetMapping("properties")
    public ZuulProperties getZuulProperties() {
        return zuulProperties;
    }

    @GetMapping("routes")
    public Map<String, ZuulProperties.ZuulRoute> getRoutes() {
        return zuulProperties.getRoutes();
    }

}
