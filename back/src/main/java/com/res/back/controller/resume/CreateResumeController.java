package com.res.back.controller.resume;

import com.res.back.pojo.Resume;
import com.res.back.service.user.resume.CreateResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateResumeController {

    @Autowired
    private CreateResumeService createResumeService;

    @PostMapping("/resume/create/")
    public String createResume(@RequestBody Resume resume) {
        return createResumeService.createResume(resume);
    }
}
