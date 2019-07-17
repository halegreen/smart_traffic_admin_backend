package com.hq.modules.network.service.impl;

import com.hq.modules.network.dao.EdgeDao;
import com.hq.modules.network.entity.NetEdgeEntity;
import com.hq.modules.network.service.EdgeService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service("edgeService")
public class EdgeServiceImpl implements EdgeService {
    @Autowired
    private EdgeDao netEdgeDao;
    @Autowired
    LaneServiceImpl laneServiceImpl;
    //第二种方法，直接在读取每个节点时将信息插入数据库
    public String addEdges(Element myroot){
        System.out.println("edge--------------------");
        List<Element> listElement=myroot.elements("edge");//所有一级子节点的list

        //当前节点的名称、文本内容和属性
        for(Element e:listElement) {
            List<Attribute> listAttr = e.attributes();//当前节点的所有属性的list
            NetEdgeEntity ne = new NetEdgeEntity();

            //每个edge为e中含有多个lane
            Iterator<Element> elementIterator = e.elementIterator();
            //通过迭代器获得当前edge下的每个lane
            while (elementIterator.hasNext()) {
                Element laneElement = elementIterator.next();
                laneServiceImpl.addLanes(laneElement);
            }

            for (Attribute attr : listAttr) {//遍历当前节点的所有属性
                String name = attr.getName();//属性名称
                String value = attr.getValue();//属性的值
                if (name.equals("id")) {
                    ne.setEdge_id(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("from_node")) {
                    ne.setFrom_node(value);
                }
                if (name.equals("to_node")) {
                    ne.setTo_node(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("edge_type")) {
                    ne.setEdge_type(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }

                //接下来三个属性，XML中不含数据，后期获得
                if (name.equals("edge_name")) {
                    ne.setEdge_name(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("lane_list")) {
                    ne.setLane_list(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("numLanes")) {
                    ne.setNumLanes(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }

            }
            netEdgeDao.insertEdges(ne);
        }

        return "ok";
    }
}
