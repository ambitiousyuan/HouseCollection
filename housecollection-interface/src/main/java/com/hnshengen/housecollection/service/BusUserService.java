package com.hnshengen.housecollection.service;

import com.hnshengen.housecollection.bean.BusUser;

import java.util.List;

public interface BusUserService {

    /**
     * 查询所有用户信息
     * @return
     */
    List<BusUser> getAllUserInfo();


}
