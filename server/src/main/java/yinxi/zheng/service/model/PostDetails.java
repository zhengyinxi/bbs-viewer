package yinxi.zheng.service.model;

import java.util.List;

/**
 * Created by zhengyinxi on 2017/2/7.
 */
public class PostDetails {
    private Post post;
    private List<Reply> replyList;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
