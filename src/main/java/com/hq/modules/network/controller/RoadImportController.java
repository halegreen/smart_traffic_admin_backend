package com.hq.modules.network.controller;

import com.hq.common.utils.R;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.service.impl.*;
import com.hq.modules.network.utils.Dom4jUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@RestController
public class RoadImportController {
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

    @RequestMapping(value = "/location/ztree/import", method = RequestMethod.GET, produces = "application/json")
    public R addNetRoad(@RequestParam(required = false, defaultValue = "0") String netRoadPath) throws DocumentException {
        Dom4jUtil dom = new Dom4jUtil();
        //获得xml根节点
        Element root = dom.parse(netRoadPath);
        int location_id = locationServiceImpl.addLocations(root);
        nodeServiceImpl.addNodes(root,location_id);
        edgeServiceImpl.addEdges(root);
        connServiceImpl.addConns(root);

        return R.ok().put("message","导入成功！");
    }
}