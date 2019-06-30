package com.hq.modules.network.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.dom4j.Element;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NodeDao extends BaseMapper<NetNodeEntity> {
    void insertNodes (NetNodeEntity netNodeEntity);
}