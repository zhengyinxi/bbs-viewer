package yinxi.zheng.service.producer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
public class JinJiang implements Producer {

    @Override
    public List getCategories() {

        return new ArrayList();
    }

    @Override
    public List getPostsList(String category, Integer pageIndex) {
        return null;
    }

    @Override
    public List getPostContent(String id, String category) {
        return null;
    }
}
