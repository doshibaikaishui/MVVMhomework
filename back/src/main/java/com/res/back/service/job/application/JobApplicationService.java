package com.res.back.service.job.application;

import java.util.Map;

public interface JobApplicationService {
    Map<String, String> applyForJob(Integer userId, Integer jobId, Integer recruiterId, String jobTitle);
    Map<String, Object> getJobApplicationsByUserId(Integer userId);
    Map<String, String> updateResumeStatus(Integer applicationId, String resumeStatus);
    Map<String, Object> getJobApplicationsByRecruiterId(Integer recruiterId);
}