package cn.netbuffer.gatewaydemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        log.info("match route:{}", route);
        log.info("method:{}", exchange.getRequest().getMethod());
        log.info("uri:{}", exchange.getRequest().getURI());
        log.info("path:{}", exchange.getRequest().getPath());
        log.info("query params:{}", exchange.getRequest().getQueryParams());
        exchange.getAttributes().put("start", System.currentTimeMillis());
        if (exchange.getRequest().getURI().getPath().endsWith("deny")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange.mutate().request(r -> r.headers(h -> h.add("your-header", "your-value"))).build());
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
