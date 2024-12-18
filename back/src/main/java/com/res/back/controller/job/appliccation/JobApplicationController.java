package com.res.back.controller.job.appliccation;

import com.res.back.service.job.application.JobApplicationService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping("/apply")
    public Map<String, String> applyForJob(@RequestParam Integer userId,
                                           @RequestParam Integer jobId,
                                           @RequestParam Integer recruiterId,
                                           @RequestParam String jobTitle) {
        return jobApplicationService.applyForJob(userId, jobId, recruiterId, jobTitle);
    }

    // 获取用户的所有投递记录
    @GetMapping("/list/{userId}")
    public Map<String, Object> getJobApplicationsByUserId(@PathVariable Integer userId) {
        return jobApplicationService.getJobApplicationsByUserId(userId);
    }

    // 更新简历状态
    @PostMapping("/update/{applicationId}")
    public Map<String, String> updateResumeStatus(@PathVariable Integer applicationId,
                                                  @RequestParam String resumeStatus) {
        return jobApplicationService.updateResumeStatus(applicationId, resumeStatus);
    }


    @GetMapping("/list")
    public Map<String, Object> getJobApplicationsByRecruiterId(@RequestParam Integer recruiterId) {
        return jobApplicationService.getJobApplicationsByRecruiterId(recruiterId);
    }
}