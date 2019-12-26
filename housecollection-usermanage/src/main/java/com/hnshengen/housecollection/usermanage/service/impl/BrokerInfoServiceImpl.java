package com.hnshengen.housecollection.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnshengen.housecollection.bean.BrokerInfo;
import com.hnshengen.housecollection.service.BrokerInfoService;
import com.hnshengen.housecollection.usermanage.mapper.BrokerInfoMapper;
import com.hnshengen.housecollection.usermanage.util.page.LayuiPageFactory;


import java.util.Map;

@Service
public class BrokerInfoServiceImpl extends ServiceImpl<BrokerInfoMapper, BrokerInfo> implements BrokerInfoService {

    @Override
    public Page<Map<String, Object>> list(String condition){
        Page page = LayuiPageFactory.defaultPage();
        return baseMapper.list(page,condition);
    }

    @Override
    public boolean add(BrokerInfo brokerInfo) {
        boolean insert = brokerInfo.insert();
        return insert;
    }

    @Override
    public void delete(Long brokerId) {
        baseMapper.deleteById(brokerId);
    }

    @Override
    public void updata(BrokerInfo brokerInfo) {
        baseMapper.updateById(brokerInfo);
    }


}
