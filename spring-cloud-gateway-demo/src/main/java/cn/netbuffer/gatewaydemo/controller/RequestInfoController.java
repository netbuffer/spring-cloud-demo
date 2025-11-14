package cn.netbuffer.gatewaydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/request")
public class RequestInfoController {

    @GetMapping("dump")
    public String dump(ServerHttpRequest request) {
        log.info("{} {} {}", request.getMethod(), request.getURI().getScheme(), request.getURI());
        List<String> names = request.getHeaders().keySet().stream().toList();
        for (String name : names) {
            log.info("{}:{}", name, request.getHeaders().getFirst(name));
        }
        return "ok";
    }
}
