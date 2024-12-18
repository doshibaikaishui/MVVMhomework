package com.res.back.controller.resume;

import com.res.back.pojo.Resume;
import com.res.back.service.user.resume.UpdateResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/resume")
public class UpdateResumeController {
    @Autowired
    private UpdateResumeService updateResumeService;

    // 更新简历
    @PutMapping("/update/")
    public String updateResume(@RequestBody Resume resume) {
        return updateResumeService.updateResume(resume);
    }
}
