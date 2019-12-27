package com.hnshengen.housecollection.usermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnshengen.housecollection.bean.BusUser;
import com.hnshengen.housecollection.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface BusUserMapper extends BaseMapper<BusUser> {
    Page<Map<String,Object>> all(@Param("page") Page page,@Param("condition") String condition);

    void deleteall();
}
