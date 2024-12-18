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
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 尝试从 User 表中查找
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);

        if (user != null) {
            System.out.println("Found User user: " + user.getUsername());
            return new UserDetailsImpl(user);
        }

        throw new UsernameNotFoundException("用户不存在");
    }

}
