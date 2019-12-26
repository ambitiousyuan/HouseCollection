package com.hnshengen.housecollection.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnshengen.housecollection.bean.BrokerInfo;
import com.hnshengen.housecollection.service.BrokerInfoService;
import com.hnshengen.housecollection.usermanage.mapper.BrokerInfoMapper;


import java.util.List;

@Service
public class BrokerInfoServiceImpl extends ServiceImpl<BrokerInfoMapper, BrokerInfo> implements BrokerInfoService {

    @Override
    public List<BrokerInfo> list(){
        QueryWrapper<BrokerInfo> brokerInfo=new QueryWrapper<>();
        List<BrokerInfo> brokerInfos = baseMapper.selectList(brokerInfo);
        return brokerInfos;
    }
}
