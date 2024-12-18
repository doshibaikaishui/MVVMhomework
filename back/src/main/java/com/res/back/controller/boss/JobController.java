package com.res.back.controller.boss;

import com.res.back.pojo.Job;
import com.res.back.service.boss.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    // 修改后的 list 方法，根据用户 ID 获取该用户发布的职位
    @GetMapping("/list/{userId}")  // 接受一个用户ID作为路径参数
    public List<Job> list(@PathVariable int userId) {
        return jobService.getJobsByUserId(userId);  // 获取该用户发布的所有职位
    }

    @GetMapping("/list")
    public List<Job> list() {
        return jobService.getAllJobs();  // 获取所有职位
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        return jobService.getJobById(id);  // 根据 ID 获取职位
    }

    @GetMapping("/detail")
    public Job getJobDetailById(@RequestParam("job_id") int id) {
        return jobService.getJobById(id);  // 根据 ID 获取职位
    }


    @PostMapping("/add")
    public String add(@RequestBody Job job) {
        // 在添加职位时，确保将发布职位的用户 ID 传入
        jobService.addJob(job);  // 添加职位
        return "success";
    }

    @PutMapping("/update")
    public String update(@RequestBody Job job) {
        jobService.updateJob(job);  // 更新职位
        return "success";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        jobService.deleteJob(id);  // 删除职位
        return "success";
    }
}
