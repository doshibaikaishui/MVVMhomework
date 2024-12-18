package com.res.back.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    private Integer id;
    private String advantages; // 个人优势
    private String experience; // 工作经验
    private String school;  //教育经历
    private String intentions; // 求职意向
    private Integer userId; //创建简历者id
}
