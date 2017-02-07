package yinxi.zheng.service.producer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import yinxi.zheng.service.model.Category;
import yinxi.zheng.service.model.Post;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.rmi.ServerError;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
@Component
public class JinJiang implements Producer {

    @Value("#{'${jijiang.categogy.id}'.split(',')}")
    private List<String> categoryIds;

    @Value("#{'${jijiang.categogy.name}'.split(',')}")
    private List<String> categoryNames;

    @Value("${jingjiang.url.postList}")
    private String postListUrl;

    @Value("${jingjiang.url.postList}")
    private String postDetailUrl;


    private List<Category> categories;


    @PostConstruct
    public void init() {
        Assert.isTrue(categoryIds.size() == categoryNames.size(), "categoryIds.size() == categoryNames.size(); check properties; ");
        this.categories = new ArrayList<Category>();
        for (int i = 0; i < categoryIds.size(); ++i) {
            Category category = new Category();
            category.setId(categoryIds.get(i));
            category.setName(categoryNames.get(i));
            category.setProducer(this);
            this.categories.add(category);
        }
    }

    @Override
    public List getCategories() {
        return this.categories;
    }

    @Override
    public List getPostsList(String categoryId, Integer pageIndex) throws ServerException {
        ArrayList<Post> result = new ArrayList<Post>();
        Document document = null;
        try {
            document = Jsoup.connect(this.postListUrl).data("board",categoryId).data("page", pageIndex.toString()).get();
        } catch (IOException e) {
            throw new ServerException("cannot connect JinJiang url", e);
        }
        String title = document.title();
//        document.getElementById("msglist")
//                .child(0)
//                .children().
        //todo 
        return result;
    }

    @Override
    public List getPostContent(String postId, String category) {
        return null;
    }
}
