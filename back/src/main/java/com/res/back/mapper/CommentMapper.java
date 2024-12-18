package com.res.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.res.back.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
