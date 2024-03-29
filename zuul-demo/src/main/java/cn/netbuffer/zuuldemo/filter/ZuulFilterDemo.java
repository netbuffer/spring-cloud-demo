package cn.netbuffer.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2019/08/30
 */
@Slf4j
public class ZuulFilterDemo extends ZuulFilter {

    @Resource
    private RouteLocator routeLocator;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        //数值越大优先级越低
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Route route = routeLocator.getMatchingRoute(ctx.getRequest().getRequestURI());
        log.info("match route:{}", route);
        log.info("debug request:{}", ctx.debugRequest());
        HttpServletRequest request = ctx.getRequest();
        log.info("method:{}", request.getMethod());
        log.info("uri:{}", request.getRequestURI());
        log.info("url:{}", request.getRequestURL());
        log.info("query string:{}", request.getQueryString());
        log.info("getContextPath:{}", request.getContextPath());
        ctx.set("start", System.currentTimeMillis());
        ctx.addZuulRequestHeader("your-header", "your-value");
        if (request.getRequestURI().endsWith("deny")) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("deny");
        }
        return null;
    }
}
