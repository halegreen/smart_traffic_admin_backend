package com.hq.modules.network.service.impl;

import com.hq.modules.network.utils.Dom4jUtil;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class NodeServiceImplTest {

    @Autowired
    NodeServiceImpl nodeService;
    @Autowired
    NodeServiceImpl nodeServiceImpl;
    @Autowired
    EdgeServiceImpl edgeServiceImpl;
    @Autowired
    LaneServiceImpl laneServiceImpl;
    @Autowired
    ConnServiceImpl connServiceImpl;
    @Autowired
    LocationServiceImpl locationServiceImpl;

    @Test
    public void addNode() throws Exception {

        System.out.println("test start ");
        Dom4jUtil dom = new Dom4jUtil();
        //测试新加其他路网元素功能
        //获得xml根节点
        Element root = dom.parse("/Users/phm/Desktop/test/demo.net.xml");

        locationServiceImpl.addLocations(root);

        nodeServiceImpl.addNodes(root);

        edgeServiceImpl.addEdges(root);

        connServiceImpl.addConns(root);

        System.out.println("ok");
    }
}
