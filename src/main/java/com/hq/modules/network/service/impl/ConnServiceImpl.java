package com.hq.modules.network.service.impl;

import com.hq.modules.network.dao.ConnDao;
import com.hq.modules.network.entity.NetConnectionEntity;
import com.hq.modules.network.service.ConnService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("connService")
public class ConnServiceImpl implements ConnService {
    @Autowired
    private ConnDao netConnDao;
        //第二种方法，直接在读取每个节点时将信息插入数据库
        public String addConns(Element myroot){
            System.out.println("connection--------------------");
            List<Element> listElement=myroot.elements("connection");//所有一级子节点的list
        /*for(Element e:listElement){//遍历所有一级子节点
            this.addNodes(e);//递归
        }*/
            //当前节点的名称、文本内容和属性
            for(Element e:listElement) {
                List<Attribute> listAttr = e.attributes();//当前节点的所有属性的list
                NetConnectionEntity ne = new NetConnectionEntity();

                for (Attribute attr : listAttr) {//遍历当前节点的所有属性
                    String name = attr.getName();//属性名称
                    String value = attr.getValue();//属性的值
                    if (name.equals("tl")) {
                        ne.setTl(value);
                        System.out.println("属性名称：" + name + "属性值：" + value);
                    }
                    if (name.equals("conn_from")) {
                        ne.setConn_from(value);
                    }
                    if (name.equals("conn_to")) {
                        ne.setConn_to(value);
                        System.out.println("属性名称：" + name + "属性值：" + value);
                    }
                    if (name.equals("fromLane")) {
                        ne.setFromLane(value);
                        System.out.println("属性名称：" + name + "属性值：" + value);
                    }

                    if (name.equals("toLane")) {
                        ne.setToLane(value);
                        System.out.println("属性名称：" + name + "属性值：" + value);
                    }
                }
                netConnDao.insertConns(ne);
            }
            return "ok";
        }
}
