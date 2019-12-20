package com.hnshengen.housecollection.house.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hnshengen.housecollection.bean.UserInfo;
import com.hnshengen.housecollection.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HouseController {

        @Reference
        UserInfoService userInfoService;

        @RequestMapping("/getAllUserInfo")
        @ResponseBody
        public List<UserInfo> getAllUserInfo(){
            List<UserInfo> allUserInfo = userInfoService.getAllUserInfo();
            return allUserInfo;
        }
}
