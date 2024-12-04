package com.res.back.controller.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/homepage/")
public class IndexController {

    @RequestMapping("index/")
    public String index() {
        return "homepage/index.html";
    }
}
