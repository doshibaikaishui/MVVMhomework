package com.res.back.service.impl.company.Comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.res.back.mapper.CommentMapper;
import com.res.back.pojo.Comment;
import com.res.back.service.company.Comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByCompanyId(Integer companyId) {
        return commentMapper.selectList(
                new QueryWrapper<Comment>().eq("company_id", companyId)
                        .orderByDesc("create_time")
        );
    }

    @Override
    public boolean addComment(Integer userId, Integer companyId, String content, String username, Integer rating) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setCompanyId(companyId);
        comment.setContent(content);
        comment.setUsername(username);
        comment.setRating(rating);

        return commentMapper.insert(comment) > 0;
    }
}