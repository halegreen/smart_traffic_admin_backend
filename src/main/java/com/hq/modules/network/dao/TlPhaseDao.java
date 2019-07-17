package com.hq.modules.network.dao;

import com.hq.modules.network.entity.TlPhaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TlPhaseDao {
    void insertTlPhases(TlPhaseEntity tlPhaseEntity);
}
