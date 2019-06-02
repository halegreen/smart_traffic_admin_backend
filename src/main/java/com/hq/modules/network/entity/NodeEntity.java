package com.hq.modules.network.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
public class NodeEntity {
    private String node_id;
    private float x;
    private float y;
    private String node_type;
    private String tl_id;
    private String node_name;

    public String getNode_id() {
        return node_id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getNode_type() {
        return node_type;
    }

    public String getTl_id() {
        return tl_id;
    }

    public String getNode_name() {
        return node_name;
    }


    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public void setTl_id(String tl_id) {
        this.tl_id = tl_id;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }
}
