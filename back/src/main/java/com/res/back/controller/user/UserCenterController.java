package com.res.back.controller.user;


import com.res.back.mapper.UserMapper;
import com.res.back.pojo.User;
import com.res.back.service.user.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
public class UserCenterController {

    @Autowired
    private UserCenterService userCenterService;

    @PostMapping("/user/account/update/")
    public Map<String, String> updateUserInfo(@RequestBody User user) {  // 使用 @RequestBody 来接收 JSON 数据并映射到 User 对象

        // 调用 Service 层更新用户信息
        return userCenterService.updateUserInfo(user.getUsername(), user.getRealName(),user.getPhoto() ,user.getEmail(), user.getPhone(), user.getGender(), user.getDegree(), user.getAge());
    }

}
