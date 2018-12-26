package co.bugu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author daocers
 * @Date 2018/12/26:11:54
 * @Description:
 */
@SpringCloudApplication
@EnableFeignClients
@MapperScan("co.bugu.*.*.dao")
@EnableRedisHttpSession
public class AppleApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppleApplication.class, args);
    }
}
