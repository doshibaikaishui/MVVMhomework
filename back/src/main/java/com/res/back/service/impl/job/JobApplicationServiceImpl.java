package com.res.back.service.impl.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.res.back.mapper.JobApplicationMapper;
import com.res.back.pojo.JobApplications;
import com.res.back.service.job.application.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationMapper jobApplicationMapper;


    @Override
    public Map<String, Object> getJobApplicationsByRecruiterId(Integer recruiterId) {
        // 调用 Mapper 中定义的自定义 SQL 查询方法
        System.out.println(recruiterId);
        List<Map<String, Object>> applications = jobApplicationMapper.getApplicationsWithResumeByRecruiterId(recruiterId);

        Map<String, Object> response = new HashMap<>();
        response.put("applications", applications);
        return response;
    }


    @Override
    public Map<String, String> applyForJob(Integer userId, Integer jobId, Integer recruiterId, String jobTitle) {
        Map<String, String> response = new HashMap<>();

        // Check if userId or jobId is invalid
        if (userId == null || jobId == null || recruiterId == null) {
            response.put("error_message", "error");
            response.put("message", "参数错误");
            return response;
        }

        // Create a new application record
        JobApplications application = new JobApplications();
        application.setUserId(userId);
        application.setJobId(jobId);
        application.setRecruiterId(recruiterId);
        application.setJobTitle(jobTitle);
        application.setResumeStatus("Pending");

        int result = jobApplicationMapper.insert(application);

        if (result > 0) {
            response.put("error_message", "success");
            response.put("message", "简历投递成功");
        } else {
            System.out.println(userId+' '+jobId+' '+recruiterId);
            response.put("error_message", "error");
            response.put("message", "简历投递失败");
        }

        return response;
    }

    @Override
    public Map<String, Object> getJobApplicationsByUserId(Integer userId) {
        // 根据 userId 查询该用户的所有投递记录
        List<JobApplications> applications = jobApplicationMapper.selectList(
                new QueryWrapper<JobApplications>().eq("user_id", userId)
        );

        Map<String, Object> response = new HashMap<>();
        System.out.println(applications);
        response.put("applications", applications);
        return response;
    }

    @Override
    public Map<String, String> updateResumeStatus(Integer applicationId, String resumeStatus) {
        Map<String, String> response = new HashMap<>();

        // 更新数据库中的状态字段
        JobApplications application = jobApplicationMapper.selectById(applicationId);

        if (application == null) {
            response.put("error_message", "error");
            response.put("message", "申请记录不存在");
            return response;
        }

        application.setResumeStatus(resumeStatus);
        int result = jobApplicationMapper.updateById(application);

        if (result > 0) {
            response.put("error_message", "success");
            response.put("message", "状态更新成功");
        } else {
            response.put("error_message", "error");
            response.put("message", "状态更新失败");
        }

        return response;
    }



}
