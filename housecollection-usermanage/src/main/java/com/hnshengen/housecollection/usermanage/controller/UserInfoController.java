package com.hnshengen.housecollection.usermanage.controller;

import com.hnshengen.housecollection.bean.UserInfo;
import com.hnshengen.housecollection.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("findAll")
    @ResponseBody
    public List<UserInfo> findAll(){
        List<UserInfo> allUserInfo = userInfoService.getAllUserInfo();
        return allUserInfo;
    }
}
