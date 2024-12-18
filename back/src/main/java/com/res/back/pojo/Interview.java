package com.res.back.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interview {
    private int id;
    private int resumeId;         // 关联的简历ID
    private int recruiterId;      // 招聘官ID
    private String message;       // 聊天消息
    private String sendTime;      // 发送时间
}
