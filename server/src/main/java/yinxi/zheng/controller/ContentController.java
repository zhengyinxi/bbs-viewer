package yinxi.zheng.controller;

import com.sun.deploy.security.BlacklistedCerts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List producers(){
        return new ArrayList();
    }
}
