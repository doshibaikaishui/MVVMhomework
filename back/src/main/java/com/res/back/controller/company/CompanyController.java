package com.res.back.controller.company;

import com.res.back.pojo.Comment;
import com.res.back.pojo.User;
import com.res.back.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public List<User> listCompanies() {
        return companyService.getCompanies();
    }

    // 根据公司 ID 获取公司详情
    @GetMapping("/detail/{companyId}")
    public User getCompanyDetail(@PathVariable("companyId") Integer companyId) {
        return companyService.getCompanyDetail(companyId);
    }


}
