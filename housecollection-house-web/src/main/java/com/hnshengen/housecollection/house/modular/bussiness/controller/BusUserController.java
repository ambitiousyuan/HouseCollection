package com.hnshengen.housecollection.house.modular.bussiness.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hnshengen.housecollection.bean.BusUser;
import com.hnshengen.housecollection.bean.UserInfo;
import com.hnshengen.housecollection.service.BusUserService;
import com.hnshengen.housecollection.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BusUserController {

        @Reference
        BusUserService busUserService;

        @ApiOperation("查询用户")
        @RequestMapping("/getAllBusUser")
        @ResponseBody
        public List<BusUser> getAllUserInfo(){
            List<BusUser> allBusUser = busUserService.getAllUserInfo();
            return allBusUser;
        }
}
