package com.res.back.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.res.back.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findUserByUsername(String username);

    @Update("UPDATE user SET real_name = #{realName}, phone = #{phone}, email = #{email}, gender = #{gender} WHERE username = #{username}")
    int updateUser(User user);

}
