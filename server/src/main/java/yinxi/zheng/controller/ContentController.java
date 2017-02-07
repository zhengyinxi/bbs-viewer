package yinxi.zheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyinxi on 2017/1/11.
 */
@Controller
@RequestMapping("/")
public class ContentController {

    @GetMapping("/producer")
    @ResponseBody
    public List producers() {
        return new ArrayList();
    }


    @GetMapping("/list")
    @ResponseBody
    public List postsList(
            @RequestParam("producer") String site,
            @RequestParam("category") String category
    ) {
        return new ArrayList();
    }
}