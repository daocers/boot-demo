package co.bugu.es.controller;

import co.bugu.es.domain.Entity;
import co.bugu.es.redis.config.JedisPoolConfig;
import co.bugu.es.redis.config.RedisConfig;
import co.bugu.es.service.ICityESService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author daocers
 * @Date 2018/12/29:11:33
 * @Description:
 */
@RestController
public class EsController {
    private Logger logger = LoggerFactory.getLogger(EsController.class);

    @Autowired
    ICityESService cityESService;
    @Autowired
    JedisPoolConfig jedisPoolConfig;
    @Autowired
    RedisConfig redisConfig;

    @RequestMapping("/add")
    public String add(String name) {
        Entity entity = new Entity();
        entity.setId(1L);
        entity.setName(name);
        cityESService.saveEntity(entity);
        return "ok";
    }

    @RequestMapping("/get")
    public List<Entity> get(String name) {
        List<Entity> list = cityESService.searchEntity(name);
        return list;
    }
}
