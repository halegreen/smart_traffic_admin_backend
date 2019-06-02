package com.hq.modules.network.controller;

import com.hq.modules.network.service.NodeService;
import com.hq.modules.network.service.impl.NodeServiceImpl;
import com.hq.modules.network.utils.Dom4jUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network")
public class NodeController {
    @Autowired
    private NodeServiceImpl nodeServiceImpl;
    public String  getNodeInfo() throws DocumentException {
        System.out.println("test controller ");
        Dom4jUtil dom = new Dom4jUtil();
        Element myroot = dom.parse("/Users/phm/Desktop/multicorss/experiment2/demo.nod.xml");
        nodeServiceImpl.getNodes(myroot);
        return "ok";
        //return nodeService.selectNode();
    }
}