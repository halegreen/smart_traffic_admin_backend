package com.hq.modules.network.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("net_edge")
public class NetEdgeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    //private int id;
    private String edge_id;//xml中对应id
    //xml中不含以下
    private String from_node;
    private String to_node;
    private String lane_list;
    private String edge_name;
    private String edge_type;
    private String numLanes;

    public String getEdge_id() {
        return edge_id;
    }

    public void setEdge_id(String edge_id) {
        this.edge_id = edge_id;
    }

    public String getFrom_node() {
        return from_node;
    }

    public void setFrom_node(String from_node) {
        this.from_node = from_node;
    }

    public String getTo_node() {
        return to_node;
    }

    public void setTo_node(String to_node) {
        this.to_node = to_node;
    }

    public String getLane_list() {
        return lane_list;
    }

    public void setLane_list(String lane_list) {
        this.lane_list = lane_list;
    }

    public String getEdge_name() {
        return edge_name;
    }

    public void setEdge_name(String edge_name) {
        this.edge_name = edge_name;
    }

    public String getEdge_type() {
        return edge_type;
    }

    public void setEdge_type(String edge_type) {
        this.edge_type = edge_type;
    }

    public String getNumLanes() {
        return numLanes;
    }

    public void setNumLanes(String numLanes) {
        this.numLanes = numLanes;
    }
}
