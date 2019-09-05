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
public class ZuulFilterDemo2 extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        long end= (long) ctx.get("start");
        log.info("url:{},cost {} s", request.getRequestURL(),(System.currentTimeMillis()-end)/1000f);
        return null;
    }
}
