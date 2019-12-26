package com.hnshengen.housecollection.usermanage.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnshengen.housecollection.bean.BrokerInfo;
import com.hnshengen.housecollection.service.BrokerInfoService;
import com.hnshengen.housecollection.usermanage.mapper.BrokerInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerInfoServiceImpl extends ServiceImpl<BrokerInfoMapper, BrokerInfo> implements BrokerInfoService {

    @Override
    public List<BrokerInfo> list(){
        BrokerInfo brokerInfo=new BrokerInfo();
        List<BrokerInfo> brokerInfos = brokerInfo.selectAll();
        return brokerInfos;
    }
}
