package com.res.back.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.res.back.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findUserByUsername(String username);

    @Update("UPDATE user SET real_name = #{realName}, phone = #{phone}, email = #{email}, gender = #{gender} ,degree = #{degree} ,age = #{age }WHERE username = #{username}")
    int updateUser(User user);

    @Insert("INSERT INTO user (username, password, real_name, gender, phone, email, company_name, company_types, company_email, company_phone, company_content) " +
            "VALUES (#{username}, #{password}, #{realName}, #{gender}, #{phone}, #{email}, #{companyName}, #{companyTypes}, #{companyEmail}, #{companyPhone}, #{companyContent})")
    int insert(User user);

    @Select("SELECT * FROM user WHERE company_name IS NOT NULL")
    List<User> getCompanies();



}
