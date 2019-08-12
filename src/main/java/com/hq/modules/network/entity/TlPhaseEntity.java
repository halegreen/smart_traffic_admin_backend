package com.hq.modules.network.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("tl_phase")
public class TlPhaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private String phase_id;
    private String duration;
    private String state;

    public String getPhase_id() {
        return phase_id;
    }

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public  String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



}