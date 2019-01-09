package co.bugu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author daocers
 * @Date 2019/1/9:13:53
 * @Description:
 */
@Component
public class MyFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
//        pre       路由之前调用
//        routing      路由之时调用
//        post  路由之后调用
//        error    发生错误时候调用


        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("method:{}, url:{}", new String[]{request.getMethod(), request.getRequestURI()});
        String accessToken = request.getParameter("token");
        if(accessToken == null){
            logger.warn("token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try{
                context.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
            return  null;
        }
        logger.info("ooooooook");
        return null;
    }
}
