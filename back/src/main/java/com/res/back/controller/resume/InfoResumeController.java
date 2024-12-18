package com.res.back.controller.resume;


import com.res.back.pojo.Resume;
import com.res.back.service.user.resume.InfoResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/resume")
public class InfoResumeController{

    @Autowired
    private InfoResumeService infoResumeService;

    // 获取当前用户简历
    @GetMapping("/info/")
    public Resume getInfoResume() {
        return infoResumeService.getInfoResume();
    }

    // 根据用户ID获取简历信息
    @GetMapping("/detail/{userId}")
    public Resume getResumeByUserId(@PathVariable Integer userId) {
        return infoResumeService.getResumeByUserId(userId);
    }
}
