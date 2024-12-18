package com.res.back.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @TableId(type = IdType.AUTO) // 自动生成主键
    private Integer id;

    @Column(name = "title")  // 职位标题
    private String title;

    @Column(name = "industry")  // 所属行业
    private String industry;

    @Column(name = "salary_min")  // 最低薪资

    private int salaryMin;

    @Column(name = "salary_max")  // 最高薪资
    private int salaryMax;

    @Column(name = "experience")  // 所需经验
    private String experience;

    @Column(name = "education")  // 所需学历
    private String education;

    @Column(name = "type")  // 职位类型
    private String type;

    @Column(name = "location")  // 工作地点
    private String location;

    @Column(name = "description")  // 职位描述
    private String description;

    @Column(name = "status")  // 职位状态（0：未发布，1：已发布）
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 格式化时间
    @Column(name = "create_time")  // 创建时间
    private Timestamp createTime;

    @Column(name = "user_id")  // 创建时间
    private Integer userId;
}
