package com.hq.modules.network.controller;

import com.hq.modules.network.service.impl.*;
import com.hq.modules.network.utils.Dom4jUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/network")
public class RoadController {

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

    public String  addNetRoad() throws DocumentException {
        System.out.println("test start");
        Dom4jUtil dom = new Dom4jUtil();
        //测试新加其他路网元素功能
        Scanner sc = new Scanner(System.in);
        System.out.println("输入要导入的路网文件的全路径，例如：\"/Users/phm/Desktop/test/demo.net.xml\"");
        System.out.println("==");
        String path = sc.nextLine();

        //获得xml根节点
        Element root = dom.parse(path);

        locationServiceImpl.addLocations(root);

        nodeServiceImpl.addNodes(root);

        edgeServiceImpl.addEdges(root);

        connServiceImpl.addConns(root);

        return "ok";
    }
}