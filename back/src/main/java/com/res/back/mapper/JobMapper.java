package com.res.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.res.back.pojo.Job;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface JobMapper extends BaseMapper<Job> {
    // 不需要再编写常规的增删改查SQL语句
    // BaseMapper 提供了基本的 CRUD 方法
}
