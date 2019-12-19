package com.hnshengen.housecollection.service;

import com.hnshengen.housecollection.bean.UserInfo;

import java.util.List;

public interface UserInfoService {

    /**
     * 查询所有用户信息
     * @return
     */
    List<UserInfo> getAllUserInfo();


}
