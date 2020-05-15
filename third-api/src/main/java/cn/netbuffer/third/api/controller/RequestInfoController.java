package cn.netbuffer.third.api.controller;

import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping(value = {"/request", "/b"})
public class RequestInfoController {

    @GetMapping("dump")
    public String dump(HttpServletRequest request) {
        log.info("{} {} {}", request.getMethod(), request.getScheme(), request.getRequestURL());
        Enumeration names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            log.info("{}:{}", name, request.getHeader(name));
        }
        try {
            log.info("request body:{}", IOUtils.toString(request.getInputStream()));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
        return "ok";
    }
}
