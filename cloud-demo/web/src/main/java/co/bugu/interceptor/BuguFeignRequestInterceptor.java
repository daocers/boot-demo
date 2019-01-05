package co.bugu.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @Author daocers
 * @Date 2019/1/5:18:33
 * @Description:
 */
public class BuguFeignRequestInterceptor implements RequestInterceptor {

    public BuguFeignRequestInterceptor() {
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Content-Type", "multipart/form-data");
    }
}
