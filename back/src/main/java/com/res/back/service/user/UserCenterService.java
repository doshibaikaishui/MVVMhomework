package com.res.back.service.user;


import com.res.back.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserCenterService {
    public Map<String, String> updateUserInfo(String username,String realName,String photo,String email,String phone,String gender,String degree, String age);
}