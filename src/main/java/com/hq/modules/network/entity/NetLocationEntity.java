package com.hq.modules.network.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("net_location")
public class NetLocationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private int id;
    private String location_name;
    private String netOffset;
    private String convBoundary;
    private String origBoundary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getNetOffset() {
        return netOffset;
    }

    public void setNetOffset(String netOffset) {
        this.netOffset = netOffset;
    }

    public String getConvBoundary() {
        return convBoundary;
    }

    public void setConvBoundary(String convBoundary) {
        this.convBoundary = convBoundary;
    }

    public String getOrigBoundary() {
        return origBoundary;
    }

    public void setOrigBoundary(String origBoundary) {
        this.origBoundary = origBoundary;
    }
}