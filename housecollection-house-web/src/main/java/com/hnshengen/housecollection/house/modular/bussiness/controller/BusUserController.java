package com.hnshengen.housecollection.house.modular.bussiness.controller;

import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.alibaba.dubbo.config.annotation.Reference;
import com.hnshengen.housecollection.bean.BusUser;
import com.hnshengen.housecollection.service.BusUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BusUserController {

    @Reference
    BusUserService busUserService;

    @ApiOperation("查询用户")
    @RequestMapping("/getAllBusUser")
    @ResponseBody
    public Object getAllUserInfo() {
        return ResponseData.success(busUserService.getAllUserInfo());
    }


    @PostMapping("/save")
    @ResponseBody
    @ApiOperation("添加用戶")
    public Object save(BusUser busUser) {
        Boolean flag = busUserService.save(busUser);
        if (flag) return ResponseData.success();
        else return ResponseData.error("添加失敗");
    }
}
