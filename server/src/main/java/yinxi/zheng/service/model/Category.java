package yinxi.zheng.service.model;

import yinxi.zheng.service.producer.Producer;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
public class Category {
    private String id;
    private String name;
    private Producer producer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
