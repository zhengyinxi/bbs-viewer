package yinxi.zheng.service.producer;

import yinxi.zheng.service.model.PostDetails;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
public interface Producer {
    String getName();
    List getCategories();
    List getPostsList(String categoryId, Integer pageIndex) throws IOException;
    PostDetails getPostContent(String postId, String categoryId) throws IOException;
}
