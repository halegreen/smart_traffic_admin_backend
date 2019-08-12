package com.hq.modules.network.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("tl_logic")
public class TlLogicEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private String tl_id;
    private String offset;
    private String phase_list;
    private String type;
    private String program_id;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getTl_id() {
        return tl_id;
    }

    public void setTl_id(String tl_id) {
        this.tl_id = tl_id;
    }

    public String getPhase_list() {
        return phase_list;
    }

    public void setPhase_list(String phase_list) {
        this.phase_list = phase_list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }
}
