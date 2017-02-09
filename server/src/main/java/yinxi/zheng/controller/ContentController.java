package yinxi.zheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yinxi.zheng.service.PostsService;
import yinxi.zheng.service.model.PostDetails;

import javax.management.RuntimeMBeanException;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyinxi on 2017/1/11.
 */
@Controller
@RequestMapping("/")
public class ContentController {

    @Autowired
    PostsService service;

    @GetMapping("/producer")
    @ResponseBody
    public List producers() {
        return this.service.getSupportedProducers();
    }

    @GetMapping("/category")
    @ResponseBody
    public List categories(@RequestParam("producer")String producer) {
        return this.service.getCategories(producer);
    }



    @GetMapping("/post")
    @ResponseBody
    public List postsList(
            @RequestParam("producer") String site,
            @RequestParam("category") String category,
            @RequestParam(name = "page", required = false, defaultValue = "1")Integer index
    ) {

        try {
            return this.service.getPostsList(category, index, site);
        } catch (IOException e) {
            throw new RuntimeException("cannot connect to server", e);
        }

    }

    //todo 分页
    @GetMapping("/post/{id}")
    @ResponseBody
    public PostDetails postsDetail(
            @RequestParam("producer") String site,
            @RequestParam("category") String category,
            @PathVariable("id") String id
    ) {

        try {
            return this.service.getPostContent(id, category, site);
        } catch (IOException e) {
            throw new RuntimeException("cannot connect to server", e);
        }

    }
}