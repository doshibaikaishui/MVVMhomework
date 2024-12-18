package com.res.back.service.impl.company;

import com.res.back.mapper.CommentMapper;
import com.res.back.mapper.UserMapper;
import com.res.back.pojo.Comment;
import com.res.back.pojo.User;
import com.res.back.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<User> getCompanies() {
        return userMapper.getCompanies();
    }

    @Override
    public User getCompanyDetail(Integer userId) {
        return userMapper.selectById(userId);
    }
}
