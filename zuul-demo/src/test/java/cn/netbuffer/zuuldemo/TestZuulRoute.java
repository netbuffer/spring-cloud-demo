package cn.netbuffer.zuuldemo;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

@Slf4j
public class TestZuulRoute {

    @Test
    public void testAdd() {
        ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
        zuulRoute.setId("taobao");
        zuulRoute.setPath("/taobao/**");
        zuulRoute.setUrl("https://www.taobao.com");
        zuulRoute.setLocation("https://www.taobao.com");
        zuulRoute.setSensitiveHeaders(Sets.newHashSet());
        log.info("add zuulroute:{}", zuulRoute);
    }
}
