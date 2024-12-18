package com.res.back.controller.user;

import com.res.back.pojo.Evaluation;
import com.res.back.service.user.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * 获取指定用户的评价列表
     *
     * @param userId 用户 ID
     * @return 评价列表
     */
    @GetMapping("/list")
    public List<Evaluation> getEvaluationsByUserId(@RequestParam Integer userId) {
        return evaluationService.getEvaluationsByUserId(userId);
    }

    /**
     * 添加评价
     *
     * @param userId      用户 ID
     * @param recruiterId 招聘者 ID
     * @param content     评价内容
     * @param rating      评分
     * @return 操作结果
     */
    @PostMapping("/add")
    public String addEvaluation(
            @RequestParam Integer userId,
            @RequestParam Integer recruiterId,
            @RequestParam String content,
            @RequestParam Integer rating,
            @RequestParam String username
    ) {
        boolean success = evaluationService.addEvaluation(userId, recruiterId, content, rating, username);
        return success ? "Evaluation added successfully" : "Failed to add evaluation";
    }
}