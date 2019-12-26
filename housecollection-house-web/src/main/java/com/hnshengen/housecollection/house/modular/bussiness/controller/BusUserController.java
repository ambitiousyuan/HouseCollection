package com.hnshengen.housecollection.house.modular.bussiness.controller;

import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnshengen.housecollection.bean.BusUser;
import com.hnshengen.housecollection.service.BusUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

@Controller
public class BusUserController {

    @Reference
    BusUserService busUserService;

    @RequestMapping("/getAllBusUser")
    @ResponseBody
    public Object getAllUserInfo(@RequestParam(value = "condition", required = false) String condition) {
        Page<Map<String, Object>> list = busUserService.getAllUserInfo(condition);
        return ResponseData.success();
    }


    @PostMapping("/save")
    @ResponseBody
    @ApiOperation("添加用戶")
    public Object save(BusUser busUser) {
        Boolean flag = busUserService.save(busUser);
        if (flag) return ResponseData.success();
        else return ResponseData.error("添加失敗");
    }

    @PostMapping("/detele")
    @ResponseBody
    @ApiOperation(("删除用户"))
    public Object delete(BusUser busUser) {
        busUserService.delete(busUser);
        return ResponseData.success();
    }
}
