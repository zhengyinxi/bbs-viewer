package yinxi.zheng.service.producer;

import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
public interface Producer {
    List getCategories();
    List getPostsList(String category, Integer pageIndex);
    List getPostContent(String id, String category);
}
