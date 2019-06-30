package com.hq.modules.network.service.impl;

import com.hq.modules.network.dao.LocaDao;
import com.hq.modules.network.entity.NetLocationEntity;
import com.hq.modules.network.service.LocationService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("locationService")

public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocaDao locaDao;
    @Override
    public String addLocations(Element myroot) {
        System.out.println("--------------------");
        List<Element> listElement=myroot.elements("location");//所有一级子节点的list

        //当前节点的名称、文本内容和属性
        for(Element e:listElement) {
            List<Attribute> listAttr = e.attributes();//当前节点的所有属性的list
            NetLocationEntity ne = new NetLocationEntity();

            for (Attribute attr : listAttr) {//遍历当前节点的所有属性
                String name = attr.getName();//属性名称
                String value = attr.getValue();//属性的值
                if (name.equals("location_id")) {
                    ne.setLocation_id(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("location_name")) {
                    ne.setLocation_name(value);
                }
                if (name.equals("netOffset")) {
                    ne.setNetOffset(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("convBoundary")) {
                    ne.setConvBoundary(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("origBoundary")) {
                    ne.setOrigBoundary(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
            }
            locaDao.insertLocations(ne);
        }
        return "ok";
    }
}
