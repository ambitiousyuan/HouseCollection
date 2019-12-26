package com.hnshengen.housecollection.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnshengen.housecollection.bean.BusUser;
import com.hnshengen.housecollection.service.BusUserService;
import com.hnshengen.housecollection.usermanage.mapper.BusUserMapper;
import com.hnshengen.housecollection.usermanage.util.page.LayuiPageFactory;

import java.util.Map;

@Service
public class BusUserServiceImpl extends ServiceImpl<BusUserMapper, BusUser> implements BusUserService{
    @Override
    public Page<Map<String, Object>> getAllUserInfo(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        QueryWrapper<BusUser> busUser = new  QueryWrapper<BusUser>();
        return baseMapper.list(page,condition);
    }

    @Override
    public boolean save(BusUser busUser) {
        return busUser.insert();
    }

    @Override
    public void delete(BusUser busUser) {
        busUser.deleteById();
    }
}
