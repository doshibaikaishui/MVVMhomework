package com.res.back.service.impl.user;

import com.res.back.mapper.UserMapper;
import com.res.back.pojo.User;
import com.res.back.service.user.BossRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class BossRegisterServiceImpl implements BossRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder; // 注入密码加密器

    @Override
    public boolean registerRecruiter(User user) {
        try {
            // 检查用户名是否已存在
            User existingUser = userMapper.findUserByUsername(user.getUsername());
            if (existingUser != null) {
                throw new IllegalArgumentException("用户名已存在！");
            }

            // 加密密码
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // 插入新用户数据
            int rowsInserted = userMapper.insert(user);
            return rowsInserted > 0;
        } catch (IllegalArgumentException e) {
            // 自定义异常处理
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("服务器内部错误，请稍后重试！");
        }
    }
}

