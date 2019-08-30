package cn.netbuffer.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2019/08/30
 */
@Slf4j
public class ZuulFilterDemo extends ZuulFilter {
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
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.info("debug request:{}", ctx.debugRequest());
        HttpServletRequest request = ctx.getRequest();
        log.info("method:{}", request.getMethod());
        log.info("url:{}", request.getRequestURL());
        log.info("query string:{}", request.getQueryString());
        log.info("getContextPath:{}", request.getContextPath());
        return null;
    }
}
