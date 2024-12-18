package com.res.back.pojo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplications {
    private Integer id;
    private Integer userId;
    private Integer jobId;
    private Integer recruiterId;

    @Column(name = "resume_status")
    private String resumeStatus;
    private Timestamp createTime;

    @Column(name = "job_title")
    private String jobTitle;

    // Getters and Setters
}
