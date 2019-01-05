package co.bugu;

import co.bugu.interceptor.BuguFeignRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @Author daocers
 * @Date 2018/12/26:14:48
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "co.bugu")
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableEurekaClient
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public BuguFeignRequestInterceptor buguFeignRequestInterceptor(){
        return new BuguFeignRequestInterceptor();
    }
}
