package yinxi.zheng.service;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
public class PostsServiceImpl implements PostsService {
    @Override
    public List getCategories(String producer) {
        return null;
    }

    @Override
    public List getPostsList(String category, Integer pageIndex, String producer) {
        return null;
    }

    @Override
    public List getPostContent(String id, String category, String producer) {
        return null;
    }
}
