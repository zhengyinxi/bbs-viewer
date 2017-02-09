package yinxi.zheng.service;

import yinxi.zheng.service.model.PostDetails;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
public interface PostsService {
    List getSupportedProducers();
    List getCategories(String producer);
    List getPostsList(String category, Integer pageIndex, String producer) throws IOException;
    PostDetails getPostContent(String id, String category, String producer) throws IOException;
}
