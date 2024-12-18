package com.res.back.service.boss;

import com.res.back.pojo.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    Job getJobById(int id);
    void addJob(Job job);
    void updateJob(Job job);
    void deleteJob(int id);
    List<Job> getJobsByUserId(int userId);
}
