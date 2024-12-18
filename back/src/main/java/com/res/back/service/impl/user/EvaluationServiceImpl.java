package com.res.back.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.res.back.mapper.EvaluationMapper;
import com.res.back.pojo.Evaluation;
import com.res.back.service.user.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

 
    @Override
    public List<Evaluation> getEvaluationsByUserId(Integer userId) {
        return evaluationMapper.selectList(
                new QueryWrapper<Evaluation>()
                        .eq("user_id", userId)
                        .orderByDesc("create_time")
        );

    }


    @Override
    public boolean addEvaluation(Integer userId, Integer recruiterId, String content, Integer rating, String username) {
        // 创建并初始化 Evaluation 对象
        Evaluation evaluation = new Evaluation();
        evaluation.setUserId(userId);
        evaluation.setRecruiterId(recruiterId);
        evaluation.setContent(content);
        evaluation.setRating(rating);
        evaluation.setCreateTime(new java.util.Date(System.currentTimeMillis())); // 将时间戳转换为 Date 对象
        evaluation.setUsername(username);

        // 插入评价到数据库
        return evaluationMapper.insert(evaluation) > 0;
    }
}