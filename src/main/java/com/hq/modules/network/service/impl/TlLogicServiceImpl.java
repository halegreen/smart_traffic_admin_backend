package com.hq.modules.network.service.impl;

import com.hq.modules.network.dao.NodeDao;
import com.hq.modules.network.dao.TlLogicDao;
import com.hq.modules.network.dao.TlPhaseDao;
import com.hq.modules.network.entity.NetEdgeEntity;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.entity.TlLogicEntity;
import com.hq.modules.network.service.TlLogicService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service("tlLogicService")
public class TlLogicServiceImpl implements TlLogicService {
    @Autowired
    private TlLogicDao tlLogicDao;
    @Autowired
    private TlPhaseServiceImpl tlPhaseServiceImpl;
    //第二种方法，直接在读取每个节点时将信息插入数据库
    @Override
    public String addTlLogics(Element myroot){
        System.out.println("TlLogic--------------------");
        List<Element> listElement=myroot.elements("tlLogic");//所有一级子节点的list

        //当前节点的名称、文本内容和属性
        for(Element e:listElement) {
            List<Attribute> listAttr = e.attributes();//当前节点的所有属性的list
            TlLogicEntity ne = new TlLogicEntity();

            //每个tllogic为e中含有多个phase
            Iterator<Element> elementIterator = e.elementIterator();
            //通过迭代器获得当前下的每个phase
            while (elementIterator.hasNext()) {
                Element phaseElement = elementIterator.next();
                tlPhaseServiceImpl.addTlPhases(phaseElement);
            }

            for (Attribute attr : listAttr) {//遍历当前节点的所有属性
                String name = attr.getName();//属性名称
                String value = attr.getValue();//属性的值
                if (name.equals("id")) {
                    ne.setTl_id(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("type")) {
                    ne.setType(value);
                }
                if (name.equals("programID")) {
                    ne.setProgram_id(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("offset")) {
                    ne.setOffset(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
                if (name.equals("phase_list")) {
                    ne.setPhase_list(value);
                    System.out.println("属性名称：" + name + "属性值：" + value);
                }
            }
            tlLogicDao.insertTlLogics(ne);
        }

        return "ok";
    }
}
