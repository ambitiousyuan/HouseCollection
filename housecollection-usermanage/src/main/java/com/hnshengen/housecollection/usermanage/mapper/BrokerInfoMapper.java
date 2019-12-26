package com.hnshengen.housecollection.usermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnshengen.housecollection.bean.BrokerInfo;

import java.util.List;

public interface BrokerInfoMapper extends BaseMapper<BrokerInfo> {

    List<BrokerInfo> list();
}
