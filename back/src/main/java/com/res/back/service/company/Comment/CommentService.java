package com.res.back.service.company.Comment;

import com.res.back.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByCompanyId(Integer companyId);

    boolean addComment(Integer userId, Integer companyId, String content, String username, Integer rating);
}