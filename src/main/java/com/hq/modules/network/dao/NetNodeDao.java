package com.hq.modules.network.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NetNodeDao extends BaseMapper<NetNodeEntity> {

    NetNodeEntity selectById(String nodeId);


}
