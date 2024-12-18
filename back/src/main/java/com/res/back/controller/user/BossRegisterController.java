package com.res.back.controller.user;

import com.res.back.pojo.User;
import com.res.back.service.user.BossRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/recruiters")
public class BossRegisterController {

    @Autowired
    private BossRegisterService bossRegisterService;

    @PostMapping("/register")
    public ResponseEntity<?> registerRecruiter(@RequestBody User user) {
        try {
            boolean success = bossRegisterService.registerRecruiter(user);
            if (success) {
                // 返回前端期望的 success 响应
                return ResponseEntity.ok(Map.of("error_message", "success"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("error_message", "注册失败，请检查输入信息！"));
            }
        } catch (IllegalArgumentException e) {
            // 返回前端期望的失败响应
            return ResponseEntity.badRequest().body(Map.of("error_message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error_message", "服务器内部错误，请稍后再试！"));
        }
    }
}
