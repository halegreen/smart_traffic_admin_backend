package com.hq.modules.network.entity;

import java.util.List;

/**
 * Created by Massive on 2016/12/26.
 */
public class ZtreeNodeEntity {

    private String id;

    private String parentId;

    private String name;

    private List<ZtreeNodeEntity> children;

    public ZtreeNodeEntity(String id, String name, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
    public ZtreeNodeEntity(String id, String name, ZtreeNodeEntity parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
    }


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ZtreeNodeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ZtreeNodeEntity> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

}

