package com.res.back.service.company;

import com.res.back.pojo.Comment;
import com.res.back.pojo.User;

import java.util.List;

public interface CompanyService {
    List<User> getCompanies();
    User getCompanyDetail(Integer userId);
}
