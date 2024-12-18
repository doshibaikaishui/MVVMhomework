package com.res.back.service.user.resume;

import com.res.back.pojo.Resume;

import java.util.Map;


public interface InfoResumeService {
    // 获取当前登录用户的简历
    Resume getInfoResume();

    // 根据用户ID获取简历信息
    Resume getResumeByUserId(Integer userId);
}
