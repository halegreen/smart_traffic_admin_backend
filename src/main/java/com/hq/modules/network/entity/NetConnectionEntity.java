package com.hq.modules.network.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("net_connection")
public class NetConnectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private String conn_from;//对应xml中from(edge)
    private String conn_to;//对应xml中to
    private String fromLane;//对应xml中fromLane(只有该车道在edge上的索引标号)
    private String toLane;//对应xml中toLane
    private String tl;//对应xml中tl

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }

    public String getConn_from() {
        return conn_from;
    }

    public void setConn_from(String conn_from) {
        this.conn_from = conn_from;
    }

    public String getConn_to() {
        return conn_to;
    }

    public void setConn_to(String conn_to) {
        this.conn_to = conn_to;
    }

    public String getFromLane() {
        return fromLane;
    }

    public void setFromLane(String fromLane) {
        this.fromLane = fromLane;
    }

    public String getToLane() {
        return toLane;
    }

    public void setToLane(String toLane) {
        this.toLane = toLane;
    }
}
