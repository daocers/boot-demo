package co.bugu.es.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author daocers
 * @Date 2019/1/3:13:38
 * @Description:
 */
@Configuration
@ConfigurationProperties("spring.redis.jedis.pool")
public class JedisPoolConfig {
    private Integer maxActive;
    private Integer maxIdle;
    private String maxWait;

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }


}
