package com.hnshengen.housecollection.house.modular.bussiness.controller;

import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.alibaba.dubbo.config.annotation.Reference;
import com.hnshengen.housecollection.service.BrokerInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/broker")
public class BrokerInfoController {

    @Reference
    BrokerInfoService brokerInfoService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list(){
        return ResponseData.success(brokerInfoService.list());
    }
}
