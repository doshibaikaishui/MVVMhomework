package com.res.back.service.user;


import com.res.back.pojo.Evaluation;

import java.util.List;

public interface EvaluationService {
    List<Evaluation> getEvaluationsByUserId(Integer userId);
    boolean addEvaluation(Integer userId, Integer recruiterId, String content, Integer rating, String username);
}
