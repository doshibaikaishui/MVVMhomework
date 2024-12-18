package com.res.back.service.user.account;


import java.util.Map;

public interface InfoService {
    public Map<String, String> getInfo();

    // 根据用户ID获取用户信息
    Map<String, String> getInfoByUserId(Integer userId);
}
