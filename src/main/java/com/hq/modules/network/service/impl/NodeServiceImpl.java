package com.hq.modules.network.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hq.modules.network.dao.NetNodeDao;
import com.hq.modules.network.dao.NodeDao;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.entity.NodeEntity;
import com.hq.modules.network.service.NodeService;
import com.hq.modules.network.utils.Dom4jUtil;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("nodeService")
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private NetNodeDao netNodeDao;

    //传入root节点
    public List<NodeEntity> getNodes(Element node){

        List<NodeEntity> nlist = new ArrayList<NodeEntity>();//用来存放所有节点，是node类型的list

        System.out.println("--------------------");
        //递归遍历当前节点 所有的子节点
        List listElement=node.elements();//所有一级子节点的list

        for(Object e:listElement){//遍历所有一级子节点
            this.getNodes((Element) e);//递归
            nlist.add((NodeEntity) e);//将每个节点信息放入nlist
        }

        //当前节点的名称、文本内容和属性
        System.out.println("当前节点名称："+node.getName());//当前节点名称
        System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称

        //对单个节点进行信息提取操作
        List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
        //新建nodeEntity对象ne
        NodeEntity ne = new NodeEntity();

        for(Attribute attr:listAttr){//遍历当前节点的所有属性
            String name=attr.getName();//属性名称
            String value=attr.getValue();//属性的值
            if(name=="id"){ne.setNode_id(value);
            System.out.println("属性名称："+name+"属性值："+value);
            };

            //将ne中的属性名为name的赋值为value，经过这个循环，已经将该节点的所有属性值都放入了ne中
            //System.out.println("属性名称："+name+"属性值："+value);
        }
        return nlist;
    }

    @Override
    public void addNode(NetNodeEntity netNodeEntity) {
        netNodeDao.insert(netNodeEntity);
    }

}
