package com.res.back.service.impl.boss;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.res.back.mapper.JobMapper;
import com.res.back.pojo.Job;
import com.res.back.service.boss.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<Job> getAllJobs() {
        // 查询所有职位
        return jobMapper.selectList(null);
    }

    @Override
    public Job getJobById(int id) {
        // 根据 ID 查询职位
        return jobMapper.selectById(id);
    }

    @Override
    public void addJob(Job job) {
        // 插入职位时，确保设置 user_id 为当前登录的招聘官 ID
        jobMapper.insert(job);
    }

    @Override
    public void updateJob(Job job) {
        // 更新职位
        jobMapper.updateById(job);
    }

    @Override
    public void deleteJob(int id) {
        // 删除职位
        jobMapper.deleteById(id);
    }

    // 新增方法，根据招聘官 user_id 获取该招聘官发布的职位
    @Override
    public List<Job> getJobsByUserId(int userId) {
        // 使用 MyBatis-Plus 查询该用户发布的所有职位
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);  // 根据 user_id 查询
        return jobMapper.selectList(queryWrapper);
    }
}
