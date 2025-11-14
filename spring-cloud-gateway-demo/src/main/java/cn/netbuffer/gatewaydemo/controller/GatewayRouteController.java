package cn.netbuffer.gatewaydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/gw")
public class GatewayRouteController {

    @Resource
    private RouteDefinitionLocator routeDefinitionLocator;

    @GetMapping("properties")
    public Mono<List<RouteDefinition>> getGatewayRouteDefinitions() {
        return routeDefinitionLocator.getRouteDefinitions().collectList();
    }

    @GetMapping("routes")
    public Mono<Map<String, String>> getRoutes() {
        return routeDefinitionLocator.getRouteDefinitions()
                .collectList()
                .map(list -> {
                    Map<String, String> routes = new LinkedHashMap<>();
                    for (RouteDefinition rd : list) {
                        routes.put(rd.getId(), rd.getPredicates().toString());
                    }
                    return routes;
                });
    }
}
