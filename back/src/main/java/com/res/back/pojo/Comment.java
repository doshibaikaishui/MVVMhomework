package com.res.back.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id; // 评论ID
    private Integer userId; // 用户ID
    private Integer companyId;
    private String content; // 评论内容

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; // 评论时间

    private String username;

    private Integer rating;
}