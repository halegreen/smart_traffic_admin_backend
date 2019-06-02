package com.hq.modules.network.utils;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class Dom4jUtil {
    /**
     * 根据地址获得一个Document(XML文件)
     * @param url  地址
     * @return Document
     * @throws DocumentException
     */

    public Element parse(String url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);// /Users/phm/Desktop/demo.nod.xml
        Element root=document.getRootElement();//获取根节点
        return root;
    }

    /**
     * 从指定节点开始,递归遍历所有子节点
     */
    public void getNodes(Element node){
        System.out.println("--------------------");
        //递归遍历当前节点所有的子节点
        List<Element> listElement=node.elements();//所有一级子节点的list
        for(Element e:listElement){//遍历所有一级子节点
            this.getNodes(e);//递归
        }
        //当前节点的名称、文本内容和属性
        System.out.println("当前节点名称："+node.getName());//当前节点名称
        System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
        List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
        for(Attribute attr:listAttr){//遍历当前节点的所有属性
            String name=attr.getName();//属性名称
            String value=attr.getValue();//属性的值
            System.out.println("属性名称："+name+"属性值："+value);
        }


    }



}
