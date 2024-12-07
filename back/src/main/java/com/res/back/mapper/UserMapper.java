package com.res.back.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.res.back.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}