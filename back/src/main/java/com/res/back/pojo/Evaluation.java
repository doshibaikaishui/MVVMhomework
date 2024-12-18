package com.res.back.pojo;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
    private Integer id;             // 评价 ID

    private Integer userId;         // 用户 ID
    @Column(name="recruiter_id")
    private Integer recruiterId;    // 招聘者 ID
    private String content;     // 评价内容
    private Integer rating;         // 评分
    @Column(name="create_time")
    private Date createTime;    // 创建时间
    private String username;
}
