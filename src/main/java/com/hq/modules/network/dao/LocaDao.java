package com.hq.modules.network.dao;

import com.hq.modules.network.entity.NetLocationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LocaDao {
    void insertLocations(NetLocationEntity netLocationEntity);
}
