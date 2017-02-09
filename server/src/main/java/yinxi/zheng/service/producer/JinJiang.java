package yinxi.zheng.service.producer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import yinxi.zheng.service.model.Category;
import yinxi.zheng.service.model.Post;
import yinxi.zheng.service.model.PostDetails;
import yinxi.zheng.service.model.Reply;

import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by zhengyinxi on 2017/2/3.
 */
@Component
public class JinJiang implements Producer {

    @Value("#{'${jinjiang.categogy.id}'.split(',')}")
    private List<String> categoryIds;

    @Value("#{'${jinjiang.categogy.name}'.split(',')}")
    private List<String> categoryNames;

    @Value("${jinjiang.url.postList}")
    private String postListUrl;

    @Value("${jinjiang.url.postDetail}")
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
    public String getName() {
        return "jinjiang";
    }

    @Override
    public List getCategories() {
        return this.categories;
    }

    @Override
    public List getPostsList(String categoryId, Integer pageIndex) throws IOException {
        if(pageIndex < 0){
            throw new IllegalArgumentException("");
        }
        List<Post> result;
        Document document;
        try {
            document = Jsoup.connect(this.postListUrl).data("board",categoryId).data("page", pageIndex.toString()).get();
        } catch (IOException e) {
            throw new IOException("cannot connect JinJiang url", e);
        }
        Elements msglist = document.getElementById("msglist")
                .child(0)
                .children();
        msglist.remove(0);
        result = msglist
                .stream()
                .map(this::constructFromTr)
                .collect(Collectors.toList());
        return result;
    }

    private Post constructFromTr(Element tr){
        Post post = new Post();
        post.setSubCategory(tr.child(0).text());
        Elements titleElement = tr.child(1).getElementsByTag("a");
        post.setTitle(titleElement.text());
        post.setLastUpdateTime(this.formatLastUpdateTime(titleElement.attr("title")));
        String id = UriComponentsBuilder
                .fromHttpUrl(titleElement.attr("abs:href"))
                .build()
                .getQueryParams()
                .get("id").get(0);
        post.setId(id);
        post.setAuthor(tr.child(2).text());
        try {
            post.setPublishTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse(tr.child(3).text().trim()));
        } catch (ParseException e) {
        }
        post.setReplyNumber(Integer.parseInt(tr.child(4).text().replaceAll("\u00A0", "")));

        return post;
    }

    private Date formatLastUpdateTime(String input){
        //最后更新：17-02-09 11:22
        DateFormat dateFormatter = new SimpleDateFormat("最后更新：yy-MM-dd HH:mm");
        try {
            return dateFormatter.parse(input);
        } catch (ParseException e) {
            throw new IllegalArgumentException("input " + input + " not match pattern (最后更新：yy-MM-dd HH:mm)");
        }

    }

    @Override
    public PostDetails getPostContent(String postId, String category) throws IOException {
        Document document = null;
        try {
            document = Jsoup.connect(this.postDetailUrl)
                    .data("board",category)
                    .data("id", postId)
                    .get();
        } catch (IOException e) {
            throw new IOException("cannot connect JinJiang url", e);
        }
        PostDetails postDetails = new PostDetails();
        postDetails.setPost(this.parsePost(postId, document));
        postDetails.setReplyList(this.parseReply(document,category,postId));
        return postDetails;
    }

    private Post parsePost(String id, Document document){
        Post post = new Post();
        post.setId(id);
        post.setReplyNumber(
                Integer.parseInt(
                        document.getElementById("msgsubject")
                                .getElementsByTag("font")
                                .text()
                                .replaceAll("[\\D]",""))
        );
        document.getElementById("msgsubject").getElementsByTag("font").remove();
        post.setTitle(document.getElementById("msgsubject").html().replaceFirst("主题：",""));
        post.setAuthor(document.getElementsByClass("authorname").first().child(0).childNode(2).outerHtml());
        post.setContent(document.getElementById("topic").text());
        try {
            post.setPublishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:yy留言")
                    .parse(document.getElementsByClass("authorname")
                            .first().child(0).childNode(4).outerHtml()));
        } catch (ParseException e) {
        }
        return post;
    }

    private List<Reply> parseReply(Document firstPage, String category, String postId) throws IOException {
        Integer totalPageNumber = 1;
        Element page_top = firstPage.getElementById("pager_top");
        if(page_top != null){
            totalPageNumber = Integer.parseInt(
                    Optional.ofNullable(page_top
                            .childNode(0)
                            .outerHtml()
                            .replaceAll("[\\D]","")
                    ).orElse("1"));
        }
        List<Document> documents = new ArrayList<Document>(){{add(firstPage);}};
        for(int i = 1; i< totalPageNumber; ++i){
            documents.add(Jsoup.connect(this.postDetailUrl)
                    .data("board",category)
                    .data("id", postId)
                    .get());
        }
        documents.stream().map(document -> {
            List<Reply> replies = new ArrayList<>();
            Element table = document.getElementsByTag("table").get(2);
            //todo 
            return replies;
        });

        List<Reply> replies = new ArrayList<>();

        return replies;
    }
}
