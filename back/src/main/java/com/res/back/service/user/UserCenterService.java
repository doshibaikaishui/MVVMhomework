package com.res.back.service.user;


import com.res.back.pojo.User;

import java.util.Map;

public interface UserCenterService {
    public Map<String, String> updateUserInfo(String username,String realName,String email,String phone,String gender);
}