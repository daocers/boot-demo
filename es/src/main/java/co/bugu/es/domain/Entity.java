package co.bugu.es.domain;

import java.io.Serializable;

/**
 * @Author daocers
 * @Date 2018/12/29:10:43
 * @Description:
 */
public class Entity implements Serializable {
    public static final String INDEX_NAME = "entity";
    public static final String TYPE = "match";

    private Long id;
    private String name;


    public Entity() {
    }

    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
