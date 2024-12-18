package com.res.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.res.back.pojo.JobApplications;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobApplicationMapper extends BaseMapper<JobApplications> {

    @Select("""
            SELECT
                job_applications.id AS applicationId,
                job_title,
                user.real_name AS candidateName,
                user.phone AS contact,
                resume_status,
                job_applications.user_id AS userId
            FROM
                job_applications 
            JOIN
                user ON job_applications.user_id = user.id
            WHERE
                job_applications .recruiter_id = #{recruiterId}
            """)
    List<Map<String, Object>> getApplicationsWithResumeByRecruiterId(@Param("recruiterId") Integer recruiterId);
}
