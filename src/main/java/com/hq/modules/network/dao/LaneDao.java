package com.hq.modules.network.dao;

import com.hq.modules.network.entity.NetLaneEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LaneDao {
    void insertLanes(NetLaneEntity netLaneEntity);
}

