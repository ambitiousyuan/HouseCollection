package com.hnshengen.housecollection.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnshengen.housecollection.bean.UserInfo;
import com.hnshengen.housecollection.service.UserInfoService;
import com.hnshengen.housecollection.usermanage.mapper.UserInfoMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService{
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> list = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName("111");
        userInfo.setPhone("15201594011");
        list.add(userInfo);

        Integer userCount = userInfoMapper.selectCount(null);
        System.out.println("---------------"+userCount);

        QueryWrapper<UserInfo> userInfoSelect = new QueryWrapper<UserInfo>();
        userInfoSelect.eq("user_info_id", "111");
        List<UserInfo> userInfos = userInfo.selectList(userInfoSelect);
        for (int i = 0; i < userInfos.size(); i++) {
            System.out.println(userInfos.get(i).getNickName());
        }

        return list;
    }
}
