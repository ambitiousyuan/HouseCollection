package com.hnshengen.housecollection.usermanage.controller;

import com.hnshengen.housecollection.service.BrokerInfoService;
import com.hnshengen.housecollection.usermanage.service.impl.BrokerInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/broker")
public class BrokerInfoController {

    @Autowired
    private BrokerInfoServiceImpl brokerInfoService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list(){
        return brokerInfoService.list();
    }
}
