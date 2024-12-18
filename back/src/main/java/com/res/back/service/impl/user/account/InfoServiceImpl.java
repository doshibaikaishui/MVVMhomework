package com.res.back.service.impl.user.account;

import com.res.back.mapper.UserMapper;
import com.res.back.pojo.User;
import com.res.back.service.impl.utils.UserDetailsImpl;
import com.res.back.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();


        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("photo", user.getPhoto());
        map.put("phone", user.getPhone());
        map.put("email", user.getEmail());
        map.put("gender", user.getGender());
        map.put("real_name", user.getRealName());
        map.put("company_name", user.getCompanyName());
        map.put("degree", user.getDegree());
        map.put("age",user.getAge());
        return map;
    }

    // 根据用户ID获取用户信息
    @Override
    public Map<String, String> getInfoByUserId(Integer userId) {
        // 根据用户ID查询用户信息
        User user = userMapper.selectById(userId);

        Map<String, String> userInfo = new HashMap<>();
        if (user != null) {
            userInfo.put("realName", user.getRealName());
            userInfo.put("phone", user.getPhone());
            userInfo.put("email", user.getEmail());
            userInfo.put("gender", user.getGender());
            userInfo.put("degree", user.getDegree());
            userInfo.put("age", String.valueOf(user.getAge()));
        }
        return userInfo;
    }
}

