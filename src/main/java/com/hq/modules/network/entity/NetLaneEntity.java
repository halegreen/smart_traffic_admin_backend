package com.hq.modules.network.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("net_lane")
public class NetLaneEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private String lane_id;//对应xml中id
    private float speed;//对应xml中speed
    private float length;//对应xml中length
    private String lane_name;

    public String getLane_id() {
        return lane_id;
    }

    public void setLane_id(String lane_id) {
        this.lane_id = lane_id;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getLane_name() {
        return lane_name;
    }

    public void setLane_name(String lane_name) {
        this.lane_name = lane_name;
    }
}