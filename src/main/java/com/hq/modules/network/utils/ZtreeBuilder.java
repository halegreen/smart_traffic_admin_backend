package com.hq.modules.network.utils;

import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.entity.ZtreeNodeEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZtreeBuilder {

    public List<ZtreeNodeEntity> beforeBuild(List<NetNodeEntity> netNodeEntityList,int Location_id){

        List<ZtreeNodeEntity> beforeTrees = new ArrayList<ZtreeNodeEntity>();
        ZtreeNodeEntity locationzTreeNode = new ZtreeNodeEntity("location_"+Location_id,"区域_"+String.valueOf(Location_id),"0");
        beforeTrees.add(locationzTreeNode);//跟节点location填入(构造函数中参数string,string,treeNode)

        for(NetNodeEntity netNodeEntity : netNodeEntityList){
            ZtreeNodeEntity zTreeNode = new ZtreeNodeEntity("node_"+netNodeEntity.getId(),"节点_"+String.valueOf(netNodeEntity.getNodeId()),locationzTreeNode);
            beforeTrees.add(zTreeNode);//添加每个node节点

            String lanes = netNodeEntity.getIncLanes();
            List<String> laneList = Arrays.asList(lanes.split(" "));//根据逗号分隔转化为list
            for(String lane : laneList){
                //lane_id不太对，好像并没有分割成每个lane，现在还是一大长串，只不过没有了逗号
                ZtreeNodeEntity zTreeLane = new ZtreeNodeEntity("node_"+netNodeEntity.getId()+"_lane_"+lane,"车道_"+lane,zTreeNode);
                beforeTrees.add(zTreeLane);//添加每个lane节点
            }
        }
        return beforeTrees;
    }

    //目前是半完成式转完成式，还需要List<netNodeEntity>转为List<ZtreeNodeEntity>格式
    /**
     * 两层循环实现建树
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<ZtreeNodeEntity> bulid(List<ZtreeNodeEntity> treeNodes) {
        List<ZtreeNodeEntity> trees = new ArrayList<ZtreeNodeEntity>();
        for (ZtreeNodeEntity treeNode : treeNodes) {
            //当父节点为o时，代表没有子节点，直接加进去
            if ("0".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            //父节点不为0，添加子节点
            for (ZtreeNodeEntity it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<ZtreeNodeEntity>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }
    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<ZtreeNodeEntity> buildByRecursive(List<ZtreeNodeEntity> treeNodes) {
        List<ZtreeNodeEntity> trees = new ArrayList<ZtreeNodeEntity>();
        for (ZtreeNodeEntity treeNode : treeNodes) {
            if ("0".equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static ZtreeNodeEntity findChildren(ZtreeNodeEntity treeNode,List<ZtreeNodeEntity> treeNodes) {
        for (ZtreeNodeEntity it : treeNodes) {
            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<ZtreeNodeEntity>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

    /*public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode("1","广州","0");
        TreeNode treeNode2 = new TreeNode("2","深圳","0");
        TreeNode treeNode3 = new TreeNode("3","天河区",treeNode1);节点元素的话 第三个位置均为location_id所在的node,lane元素的第三个均为所属的node
        TreeNode treeNode4 = new TreeNode("4","越秀区",treeNode1);
        TreeNode treeNode5 = new TreeNode("5","黄埔区",treeNode1);
        TreeNode treeNode6 = new TreeNode("6","石牌",treeNode3);
        TreeNode treeNode7 = new TreeNode("7","百脑汇",treeNode6);
        TreeNode treeNode8 = new TreeNode("8","南山区",treeNode2);
        TreeNode treeNode9 = new TreeNode("9","宝安区",treeNode2);
        TreeNode treeNode10 = new TreeNode("10","科技园",treeNode8);
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(treeNode1);
        list.add(treeNode2);
        list.add(treeNode3);
        list.add(treeNode4);
        list.add(treeNode5);
        list.add(treeNode6);
        list.add(treeNode7);
        list.add(treeNode8);
        list.add(treeNode9);
        list.add(treeNode10);
        List<TreeNode> trees = TreeBuilder.bulid(list);
        List<TreeNode> trees_ = TreeBuilder.buildByRecursive(list);
    }*/
}
