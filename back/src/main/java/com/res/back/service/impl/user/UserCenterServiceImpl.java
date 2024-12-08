package com.res.back.service.impl.user;
import com.res.back.mapper.UserMapper;
import com.res.back.pojo.User;
import com.res.back.service.user.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserCenterServiceImpl implements UserCenterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> updateUserInfo(String userName,String realName, String email, String phone, String gender) {
        Map<String, String> response = new HashMap<>();


        // 获取当前登录用户的用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 当前登录的用户名

        // 输入参数验证
        if (realName == null || realName.trim().equals("")) {
            response.put("error_message", "error");
            response.put("message", "真实姓名不能为空");
            return response;
        }
        if (email == null || email.trim().equals("")) {
            response.put("error_message", "error");
            response.put("message", "邮箱不能为空");
            return response;
        }
        if (phone == null || phone.trim().equals("")) {
            response.put("error_message", "error");
            response.put("message", "手机号不能为空");
            return response;
        }
        if (gender == null || gender.trim().equals("")) {
            response.put("error_message", "error");
            response.put("message", "性别不能为空");
            return response;
        }

        // 从数据库中查询用户
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            response.put("error_message", "error");
            response.put("message", "用户不存在");
            return response;
        }


        

        // 更新用户信息
        user.setRealName(realName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);




        int updatedRows = userMapper.updateUser(user);
        if (updatedRows > 0) {
            response.put("error_message", "success");
            response.put("message", "用户信息更新成功");
            response.put("id", String.valueOf(user.getId())); // 返回用户的 ID
        } else {
            response.put("error_message", "error");
            response.put("message", "用户信息更新失败");
            response.put("id", String.valueOf(user.getId())); // 返回用户的 ID
        }
        System.out.println("Received data: " + response); // 打印接收到的数据

        return response;
    }
}