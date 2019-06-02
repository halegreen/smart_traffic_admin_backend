package com.hq.modules.network.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hq.modules.network.entity.NodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
//extends BaseMapper<NodeEntity>
@Mapper
@Repository
public interface NodeDao{
    public NodeEntity selectNode();
}
