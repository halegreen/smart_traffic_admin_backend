package com.hq.modules.network.dao;

import com.hq.modules.network.entity.NetEdgeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EdgeDao {
    void insertEdges(NetEdgeEntity netEdgeEntity);
}
