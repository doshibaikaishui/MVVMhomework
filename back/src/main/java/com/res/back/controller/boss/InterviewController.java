package com.res.back.controller.boss;

import com.res.back.pojo.Interview;
import com.res.back.service.boss.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @GetMapping("/messages/{resumeId}")
    public List<Interview> getMessages(@PathVariable int resumeId) {
        return interviewService.getChatMessages(resumeId);
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody Interview message) {
        interviewService.addMessage(message);
        return "success";
    }
}

