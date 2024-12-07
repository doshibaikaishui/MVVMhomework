package com.res.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.res.back.mapper.UserMapper;
import com.res.back.pojo.User;
import com.res.back.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //快捷键 windowsalt + insert / mac是option + enter
    @Autowired
    private UserMapper userMapper;
    // 传入 username 返回对应的信息，在这里也就是id name pwd
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        return new UserDetailsImpl(user);
    }
}
