package com.hq.modules.network.service.impl;

import com.hq.common.utils.R;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.entity.ZtreeNodeEntity;
import com.hq.modules.network.utils.Dom4jUtil;
import com.hq.modules.network.utils.ZtreeBuilder;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    ZtreeServiceImpl ztreeServiceImpl;

    @Test
    public void addNode() throws Exception {
        System.out.println("test start ");
        Dom4jUtil dom = new Dom4jUtil();
        //测试新加其他路网元素功能
        //获得xml根节点
        Element root = dom.parse("/Users/phm/Desktop/test/demo.net.xml");
        int location_id = locationServiceImpl.addLocations(root);
        nodeServiceImpl.addNodes(root, location_id);
        edgeServiceImpl.addEdges(root);
        connServiceImpl.addConns(root);
        System.out.println("ok");
    }

    @Test
    public void getNodesForZtree() {
        List<Integer> locationList = locationServiceImpl.selectLocations();
        List ztreeNodesLists = new ArrayList<>();
        for (int location_id : locationList) {
            //通过location_id获取该地区的所有nodeList
            List<NetNodeEntity> netNodeEntityList = new ArrayList<NetNodeEntity>();
            netNodeEntityList = ztreeServiceImpl.getNodesByLocationId(location_id);
            //将List<NetNodeEntity>类型转换为前端ztree展示需要的List<ZtreeNodeEntity>类型
            ZtreeBuilder ztreeBuilder = new ZtreeBuilder();
            List<ZtreeNodeEntity> beforeZtree = ztreeBuilder.beforeBuild(netNodeEntityList, location_id);
            //第一种bulid方法两层循环建树
            List<ZtreeNodeEntity> ztreeNodesList = ztreeBuilder.bulid(beforeZtree);
            ztreeNodesLists.add(ztreeNodesList);
            //第二种buildByRecursive方法递归方法建树
            // List<ZtreeNodeEntity> ztreeNodesList = ztreeBuilder.buildByRecursive(beforeZtree);
        }
    }
}