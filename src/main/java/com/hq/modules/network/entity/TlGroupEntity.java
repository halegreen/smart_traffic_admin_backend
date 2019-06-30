package com.hq.modules.network.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("tl_group")
public class TlGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private String group_id;
    private String logic_list;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getLogic_list() {
        return logic_list;
    }

    public void setLogic_list(String logic_list) {
        this.logic_list = logic_list;
    }
}