package com.res.back.service.boss;

import com.res.back.pojo.Interview;

import java.util.List;

public interface InterviewService {
    List<Interview> getChatMessages(int resumeId);
    void addMessage(Interview message);
}
