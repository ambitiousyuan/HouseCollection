package com.hnshengen.housecollection.service;

import com.hnshengen.housecollection.bean.BrokerInfo;

import java.util.List;

public interface BrokerInfoService {

    /**
     * 查询所有的经纪人
     * @return
     */
    List<BrokerInfo> list();

    boolean add(BrokerInfo brokerInfo);

    void delete(Long brokerId);
}
