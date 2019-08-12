package com.hq.modules.network.dao;

import com.hq.modules.network.entity.NetConnectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ConnDao {
    void insertConns(NetConnectionEntity netConnectionEntity);
}
