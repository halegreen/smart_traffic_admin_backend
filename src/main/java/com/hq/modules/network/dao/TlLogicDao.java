package com.hq.modules.network.dao;

import com.hq.modules.network.entity.TlLogicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TlLogicDao {
    void insertTlLogics(TlLogicEntity tlLogicEntity);
}
