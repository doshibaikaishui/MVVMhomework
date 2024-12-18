package com.res.back.controller.user.account;


import com.res.back.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/user/account/info/")
    public Map<String, String> getInfo() {
        return infoService.getInfo();
    }

    // 根据用户ID获取用户信息
    @GetMapping("/user/account/info/{userId}")
    public Map<String, String> getInfo(@PathVariable Integer userId) {
        return infoService.getInfoByUserId(userId);
    }
}