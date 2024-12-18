package com.res.back.mapper;

import com.res.back.pojo.Interview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterviewMapper {
    List<Interview> getChatMessages(int resumeId);
    int addMessage(Interview message);
}
