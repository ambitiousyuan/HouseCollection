package com.hnshengen.housecollection.usermanage.service.impl;

import com.hnshengen.housecollection.bean.UserInfo;
import com.hnshengen.housecollection.service.UserInfoService;
import com.hnshengen.housecollection.usermanage.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> list = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName("111");
        userInfo.setPhone("15201594011");
        list.add(userInfo);
        return list;
    }
}
