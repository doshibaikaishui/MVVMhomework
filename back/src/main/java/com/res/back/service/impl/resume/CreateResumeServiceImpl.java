package com.res.back.service.impl.resume;

import com.res.back.mapper.ResumeMapper;
import com.res.back.pojo.Resume;
import com.res.back.service.user.resume.CreateResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateResumeServiceImpl implements CreateResumeService {

    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    public String createResume(Resume resume) {
        System.out.println(resume);
        if (resume == null || resume.getUserId() == null) {
            return "简历数据不完整";
        }

        int result = resumeMapper.insert(resume);
        return result > 0 ? "success" : "创建失败";
    }
}
