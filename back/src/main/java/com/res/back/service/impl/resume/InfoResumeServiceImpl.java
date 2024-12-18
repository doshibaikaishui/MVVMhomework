package com.res.back.service.impl.resume;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.res.back.mapper.ResumeMapper;
import com.res.back.pojo.Resume;
import com.res.back.service.impl.utils.UserDetailsImpl;
import com.res.back.service.user.resume.InfoResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class InfoResumeServiceImpl implements InfoResumeService {

    @Autowired
    private ResumeMapper resumeMapper;

    // 后端返回查询到的简历
    @Override
    public Resume getInfoResume() {
        // 获取当前登录用户信息
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();

        // 查询简历信息
        Resume resume = resumeMapper.selectOne(new QueryWrapper<Resume>().eq("user_id", userId));
        if (resume != null) {
            return resume;  // 成功返回简历数据
        } else {
            return null;  // 如果简历未找到，返回null
        }
    }

    // 根据用户ID查询简历信息
    @Override
    public Resume getResumeByUserId(Integer userId) {
        // 使用MyBatis-Plus的QueryWrapper来根据user_id查询简历信息
        Resume resume = resumeMapper.selectOne(new QueryWrapper<Resume>().eq("user_id", userId));
        return resume;  // 返回查询到的简历信息，如果没有找到会返回null
    }
}

