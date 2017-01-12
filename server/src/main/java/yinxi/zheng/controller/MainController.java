package yinxi.zheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhengyinxi on 2017/1/11.
 */
@Controller
public class MainController {
    @GetMapping("/echo")
    @ResponseBody
    public String echo(){
        return "hello world";
    }
}
