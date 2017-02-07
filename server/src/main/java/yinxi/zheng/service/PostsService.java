package yinxi.zheng.service;

import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
public interface PostsService {
    List getCategories(String producer);
    List getPostsList(String category, Integer pageIndex, String producer);
    List getPostContent(String id, String category, String producer);
}
