package com.hq.modules.network.dao;

import com.hq.modules.network.entity.NetLocationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LocaDao {
    void insertLocations(NetLocationEntity netLocationEntity);
    List<Integer> selectLocations();
}
