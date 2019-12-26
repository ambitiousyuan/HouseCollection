package com.hnshengen.housecollection.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnshengen.housecollection.bean.BusUser;
import com.hnshengen.housecollection.service.BusUserService;
import com.hnshengen.housecollection.usermanage.mapper.BusUserMapper;

import java.util.List;

@Service
public class BusUserServiceImpl extends ServiceImpl<BusUserMapper, BusUser> implements BusUserService{
    @Override
    public List<BusUser> getAllUserInfo() {
        QueryWrapper<BusUser> busUser = new  QueryWrapper<BusUser>();
        return baseMapper.selectList(busUser);
    }

    @Override
    public boolean save(BusUser busUser) {
        return busUser.insert();
    }
}
