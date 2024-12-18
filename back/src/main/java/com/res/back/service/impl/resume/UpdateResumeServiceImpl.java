package com.res.back.service.impl.resume;

import com.res.back.mapper.ResumeMapper;
import com.res.back.pojo.Resume;
import com.res.back.service.impl.utils.UserDetailsImpl;
import com.res.back.service.user.resume.UpdateResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UpdateResumeServiceImpl implements UpdateResumeService {

    @Autowired
    private ResumeMapper resumeMapper;

    // 后端更新简历返回操作结果
    @Override
    public String updateResume(Resume resume) {
        // 获取当前登录用户信息
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();

        // 确保用户只能更新自己的简历
        if (!resume.getUserId().equals(userId)) {
            return "无权限修改他人简历！";  // 返回错误信息
        }

        // 更新简历
        int rows = resumeMapper.updateById(resume);
        return rows > 0 ? "更新成功" : "更新失败";
    }

}
