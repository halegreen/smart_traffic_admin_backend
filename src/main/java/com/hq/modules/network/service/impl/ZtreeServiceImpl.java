package com.hq.modules.network.service.impl;

import com.hq.modules.executor.service.RestfulApiService;
import com.hq.modules.executor.service.impl.AbstractApiService;
import com.hq.modules.network.dao.NodeDao;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.service.ZtreeService;
import com.shaw.common.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
//相当于TaskApiService
//不知道继承这两个有啥用，但其他的也都继承了，先写上
public class ZtreeServiceImpl extends AbstractApiService implements RestfulApiService, ZtreeService {

    @Autowired
    private NodeDao nodeDao;

    private static Logger logger = LoggerFactory.getLogger(ZtreeServiceImpl.class);

    //通过设置uri来初始化
    @PostConstruct
    public void init() {
        this.setUri("location/test");
    }

    //获得该区域所有节点
    public List<NetNodeEntity> getNodesByLocationId(int location_id){
        List<NetNodeEntity> nodeEntityList = nodeDao.getNodesByLocationId(location_id);
        return nodeEntityList;
    }

    @Override
    public NetNodeEntity get(String id) {
        return (NetNodeEntity) super.get(id);
    }

    //是从后台获得执行需要的参数，不是实体类中的属性
    public void add(NetNodeEntity ne) {
        NetNodeEntity netNodeEntity = new NetNodeEntity();
    }

    public void update(TaskInfo info, String id) {
        super.update(info, id);
    }
}
