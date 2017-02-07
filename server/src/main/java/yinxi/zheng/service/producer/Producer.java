package yinxi.zheng.service.producer;

import java.rmi.ServerException;
import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
public interface Producer {
    List getCategories();
    List getPostsList(String categoryId, Integer pageIndex) throws ServerException;
    List getPostContent(String postId, String categoryId);
}
