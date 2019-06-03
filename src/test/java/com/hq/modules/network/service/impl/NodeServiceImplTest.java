package com.hq.modules.network.service.impl;

import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.service.NodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NodeServiceImplTest {

    @Autowired
    NodeService nodeService;

    @Test
    public void addNode() throws Exception {
        NetNodeEntity netNodeEntity = new NetNodeEntity();
        netNodeEntity.setNodeId("0000");
        netNodeEntity.setNodeName("xxxx");
        netNodeEntity.setNodeType("laneww");
        netNodeEntity.setTlId("sss");
        netNodeEntity.setX(82.73f);
        netNodeEntity.setY(73.52f);
        nodeService.addNode(netNodeEntity);
    }

}