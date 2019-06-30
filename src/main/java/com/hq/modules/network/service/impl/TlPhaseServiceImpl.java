package com.hq.modules.network.service.impl;

import com.hq.modules.network.dao.TlPhaseDao;
import com.hq.modules.network.entity.TlPhaseEntity;
import com.hq.modules.network.service.TlPhaseService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("tlPhaseService")
public class TlPhaseServiceImpl implements TlPhaseService {
    @Autowired
    private TlPhaseDao tlPhaseDao;
    //第二种方法，直接在读取每个节点时将信息插入数据库
    @Override
    public String addTlPhases(Element myroot){
        System.out.println("Tlphases--------------------");
        List<Element> listElement=myroot.elements("phase");//所有一级子节点的list

        //当前节点的名称、文本内容和属性
        for(Element e:listElement) {
            List<Attribute> listAttr = e.attributes();//当前节点的所有属性的list
            TlPhaseEntity ne = new TlPhaseEntity();

            for (Attribute attr : listAttr) {//遍历当前节点的所有属性
                String name = attr.getName();//属性名称
                String value = attr.getValue();//属性的值
                if (name.equals("duration")) {
                    ne.setDuration(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("state")) {
                    ne.setState(value);
                }
                if (name.equals("phase_id")) {
                    ne.setPhase_id(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
            }
            tlPhaseDao.insertTlPhases(ne);
        }
        return "ok";
    }
}
