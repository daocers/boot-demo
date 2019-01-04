package co.bugu.es.service.impl;

import co.bugu.es.domain.Entity;
import co.bugu.es.service.ICityESService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author daocers
 * @Date 2018/12/29:10:47
 * @Description:
 */
@Service
public class CityESServiceImpl implements ICityESService {
    private Logger logger = LoggerFactory.getLogger(CityESServiceImpl.class);

    @Autowired
    JestClient jestClient;

    @Override
    public void saveEntity(Entity entity) {
        Index index = new Index.Builder(entity).index(Entity.INDEX_NAME).type(Entity.TYPE).build();
        try {
            jestClient.execute(index);
            logger.info("es 插入完成");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("保存失败", e);
        }
    }

    @Override
    public void saveEntity(List<Entity> entityList) {
        Bulk.Builder bulk = new Bulk.Builder();
        for (Entity entity : entityList) {
            Index index = new Index.Builder(entity).index(Entity.INDEX_NAME).type(Entity.TYPE).build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
            logger.info("es 插入完成");
        } catch (IOException e) {
            logger.error("插入失败", e);
        }
    }

    @Override
    public List<Entity> searchEntity(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", searchContent));
//        searchSourceBuilder.aggregation(AggregationBuilders.terms("top_10_states").field("name").size(10));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(Entity.INDEX_NAME).addType(Entity.TYPE).build();

        try {
            JestResult result = jestClient.execute(search);
            return result.getSourceAsObjectList(Entity.class);
        } catch (Exception e) {
            logger.error("查询失败", e);
        }
        return null;
    }
}
