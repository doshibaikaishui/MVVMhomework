package com.res.back.controller.company.comment;

import com.res.back.pojo.Comment;
import com.res.back.service.company.Comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public List<Comment> getComments(@RequestParam Integer companyId) {
        return commentService.getCommentsByCompanyId(companyId);
    }

    @PostMapping("/add")
    public String addComment(
            @RequestParam Integer userId,
            @RequestParam Integer companyId,
            @RequestParam String content,
            @RequestParam String username,
            @RequestParam Integer rating
    ) {
        boolean success = commentService.addComment(userId, companyId, content, username, rating);
        return success ? "Comment added successfully" : "Failed to add comment";
    }
}