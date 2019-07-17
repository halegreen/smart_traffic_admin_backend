package com.hq.modules.network.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 路网节点
 */

@TableName("net_node")
public class NetNodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private int id;
    private String nodeId;//xml中对应id
    private String x;//xml中对应x
    private String y;//xml中对应y
    private String nodeType;//xml中对应type
    private String incLanes;
    private String intLanes;
    //接下来的是xml中不一定含有的数据
    private String tlId;
    private String nodeName;
    private int location_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getIncLanes() {
        return incLanes;
    }

    public void setIncLanes(String incLanes) {
        this.incLanes = incLanes;
    }

    public String getIntLanes() {
        return intLanes;
    }

    public void setIntLanes(String intLanes) {
        this.intLanes = intLanes;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getTlId() {
        return tlId;
    }

    public void setTlId(String tlId) {
        this.tlId = tlId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
