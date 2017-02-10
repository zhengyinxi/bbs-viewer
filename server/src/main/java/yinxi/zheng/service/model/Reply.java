package yinxi.zheng.service.model;

import java.util.Date;

/**
 * Created by zhengyinxi on 2017/2/7.
 */
public class Reply {
    private String id;
    private String content;
    private Date time;
    private String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
