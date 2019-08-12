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
    //通过导入xml来向数据库填数据
    void insertNodes (NetNodeEntity netNodeEntity);
    //获取该区域所有节点来提供给ztree前端展示
    List<NetNodeEntity> getNodesByLocationId(int locationId);
}