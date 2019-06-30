package com.hq.modules.network.service.impl;

import com.hq.modules.network.dao.NodeDao;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.service.NodeService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("nodeService")
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeDao nodeDao;
    //第二种方法，直接在读取每个节点时将信息插入数据库
    public String addNodes(Element myroot){
        System.out.println("junction--------------------");
        List<Element> listElement=myroot.elements("junction");//所有一级子节点的list

        //当前节点的名称、文本内容和属性
        for(Element e:listElement) {
            List<Attribute> listAttr = e.attributes();//当前节点的所有属性的list
            NetNodeEntity ne = new NetNodeEntity();

            for (Attribute attr : listAttr) {//遍历当前节点的所有属性
                String name = attr.getName();//属性名称
                String value = attr.getValue();//属性的值
                if (name.equals("id")) {
                    ne.setNodeId(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("x")) {
                    ne.setX(value);
                }
                if (name.equals("y")) {
                    ne.setY(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("type")) {
                    ne.setNodeType(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("tl")) {
                    ne.setTlId(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("incLanes")) {
                    ne.setIncLanes(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("intLanes")) {
                    ne.setIntLanes(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
            }

            nodeDao.insertNodes(ne);
        }
        return "ok";
    }
}

