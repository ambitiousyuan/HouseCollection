package com.hnshengen.housecollection.house.modular.bussiness.controller;

import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.alibaba.dubbo.config.annotation.Reference;
import com.hnshengen.housecollection.bean.BrokerInfo;
import com.hnshengen.housecollection.service.BrokerInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/broker")
public class BrokerInfoController {

    @Reference
    BrokerInfoService brokerInfoService;

    /**
     * 查询所有的经纪人
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(){
        return ResponseData.success(brokerInfoService.list());
    }

    /**
     * 添加经纪人
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(BrokerInfo brokerInfo){
        boolean insert= brokerInfoService.add(brokerInfo);
        if (insert){
            return ResponseData.success();
        }
        return ResponseData.error(401,"添加失败");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long brokerId){
        brokerInfoService.delete(brokerId);
        return ResponseData.success();

    }

}
