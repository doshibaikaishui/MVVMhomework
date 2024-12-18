package com.res.back.service.impl.boss;

import com.res.back.mapper.InterviewMapper;
import com.res.back.pojo.Interview;
import com.res.back.service.boss.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    private InterviewMapper interviewMapper;

    @Override
    public List<Interview> getChatMessages(int resumeId) {
        return interviewMapper.getChatMessages(resumeId);
    }

    @Override
    public void addMessage(Interview message) {
        interviewMapper.addMessage(message);
    }
}
