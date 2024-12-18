package com.res.back.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @TableId(type = IdType.AUTO)
    private Integer id; // 主键id，自动生成

    private String username; // 用户名

    private String password; // 密码

    private String photo; // 头像照片路径

    private String phone; // 电话号码

    private String gender; // 性别（男/女）

    private String email; // 邮箱

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; // 创建时间

    @Column(name = "real_name")
    @JsonProperty("real_name")
    private String realName; // 真实姓名

    // 企业相关字段
    @Column(name = "company_photo")
    @JsonProperty("company_photo")
    private String companyPhoto; // 企业照片路径

    @Column(name = "company_name")
    @JsonProperty("company_name")
    private String companyName; // 企业名称

    @Column(name = "company_types")
    @JsonProperty("company_types")
    private String companyTypes; // 企业类型

    @Column(name = "company_email")
    @JsonProperty("company_email")
    private String companyEmail; // 企业邮箱

    @Column(name = "company_phone")
    @JsonProperty("company_phone")
    private String companyPhone; // 企业电话

    @Column(name = "company_content")
    @JsonProperty("company_content")
    private String companyContent; // 企业介绍

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "company_create_time")
    @JsonProperty("company_create_time")
    private String companyCreateTime; // 企业创建时间

    private String degree;

    private String age;

    public User(Integer id, String username, String password, String photo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.photo = photo;
    }

}