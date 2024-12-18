package com.res.back.service.user;

import com.res.back.pojo.User;

public interface BossRegisterService {
    /**
     * 招聘官注册服务
     * @param user 用户信息
     * @return 注册结果
     */
    boolean registerRecruiter(User user);
}
