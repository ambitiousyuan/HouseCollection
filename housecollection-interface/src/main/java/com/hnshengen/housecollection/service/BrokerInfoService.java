package com.hnshengen.housecollection.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnshengen.housecollection.bean.BrokerInfo;

import java.util.List;
import java.util.Map;

public interface BrokerInfoService {

    /**
     * 查询所有的经纪人
     * @return
     */
    Page<Map<String, Object>> list(String condition);

    boolean add(BrokerInfo brokerInfo);

    void delete(Long brokerId);

    void updata(BrokerInfo brokerInfo);
}
