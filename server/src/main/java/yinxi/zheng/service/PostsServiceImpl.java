package yinxi.zheng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import yinxi.zheng.config.ApplicationConfiguration;
import yinxi.zheng.service.model.PostDetails;
import yinxi.zheng.service.producer.Producer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    ApplicationContext context;

    @Autowired
    List<Producer> producers;

    private Map<String, Producer> supportedProducers;

    @PostConstruct
    void setSupportedProducers(){
        this.supportedProducers =
                this.producers.stream().collect(Collectors.toMap(Producer::getName, item -> item));
    }

    @Override
    public List getSupportedProducers() {
        return this.producers.stream()
                .map(p->new HashMap<String, String>(){{
                    put("name",p.getName());
                }})
                .collect(Collectors.toList());
    }

    private Producer getProducer(String producerName){
        Producer producer = this.supportedProducers.get(producerName);
        if(producer == null){
            throw new IllegalArgumentException(producerName + "not supported");
        }
        return producer;
    }


    @Override
    public List getCategories(String producerName) {
        return this.getProducer(producerName).getCategories();
    }


    @Override
    //todo add retry if catch io exception
    //@Retryable(maxAttempts=5)
    public List getPostsList(String category, Integer pageIndex, String producerName) throws IOException {
        return this.getProducer(producerName).getPostsList(category, pageIndex);
    }

    @Override
    public PostDetails getPostContent(String id, String category, String producerName) throws IOException {
        return this.getProducer(producerName).getPostContent(id, category);
    }
}
