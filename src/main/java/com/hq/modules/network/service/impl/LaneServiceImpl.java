package com.hq.modules.network.service.impl;

import com.hq.modules.network.dao.LaneDao;
import com.hq.modules.network.entity.NetLaneEntity;
import com.hq.modules.network.service.LaneService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("laneService")
public class LaneServiceImpl implements LaneService {

    @Autowired
    private LaneDao netLaneDao;
    public String addLanes(Element onelane){
        System.out.println("lane--------------------");

        NetLaneEntity ne = new NetLaneEntity();
        List<Attribute> listAttr = onelane.attributes();//当前节点的所有属性的list
            for (Attribute attr : listAttr) {//遍历当前节点的所有属性
                String name = attr.getName();//属性名称
                String value = attr.getValue();//属性的值
                if (name.equals("id")) {
                    ne.setLane_id(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("speed")) {
                    float speed = Float.parseFloat(value);
                    ne.setSpeed(speed);
                }
                if (name.equals("length")) {
                    float length = Float.parseFloat(value);
                    ne.setLength(length);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("lane_name")) {
                    ne.setLane_name(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
            }
            netLaneDao.insertLanes(ne);
        return "ok";
    }
}