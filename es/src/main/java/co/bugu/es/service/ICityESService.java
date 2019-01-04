package co.bugu.es.service;

import co.bugu.es.domain.Entity;

import java.util.List;

/**
 * @Author daocers
 * @Date 2018/12/29:10:46
 * @Description:
 */
public interface ICityESService {
    void saveEntity(Entity entity);

    void saveEntity(List<Entity> entityList);

    List<Entity> searchEntity(String searchContent);
}
