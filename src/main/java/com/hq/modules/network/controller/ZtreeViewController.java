package com.hq.modules.network.controller;

import com.hq.common.utils.R;
import com.hq.modules.network.entity.NetNodeEntity;
import com.hq.modules.network.entity.ZtreeNodeEntity;
import com.hq.modules.network.service.impl.LocationServiceImpl;
import com.hq.modules.network.service.impl.NodeServiceImpl;
import com.hq.modules.network.service.impl.ZtreeServiceImpl;
import com.hq.modules.network.utils.ZtreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ZtreeViewController {
    @Autowired
    ZtreeServiceImpl ztreeServiceImpl;
    @Autowired
    NodeServiceImpl nodeServiceImpl;
    @Autowired
    LocationServiceImpl locationServiceImpl;

    @RequestMapping(value = "/location/ztree/list", method = RequestMethod.GET, produces = "application/json")
    public R getNodesForZtree(){
        List ztreeNodesLists = new ArrayList<>();
        //获取所有location_id
        List<Integer> locationList = locationServiceImpl.selectLocations();
        ZtreeBuilder ztreeBuilder = new ZtreeBuilder();
        for(int location_id : locationList) {
            //通过location_id获取该地区的netNodeEntityList
            List<NetNodeEntity> netNodeEntityList = ztreeServiceImpl.getNodesByLocationId(location_id);
            //将List<NetNodeEntity>类型转换为前端ztree展示需要的List<ZtreeNodeEntity>类型
            List<ZtreeNodeEntity> beforeZtree = ztreeBuilder.beforeBuild(netNodeEntityList, location_id);
            //第一种bulid方法两层循环建树
            List<ZtreeNodeEntity> ztreeNodesList = ztreeBuilder.bulid(beforeZtree);
            //第二种buildByRecursive方法递归方法建树
            // List<ZtreeNodeEntity> ztreeNodesList = ztreeBuilder.buildByRecursive(beforeZtree);
            ztreeNodesLists.add(ztreeNodesList);//将该location的树放入ztreeNodesLists
        }
        return R.ok().put("ztreeNodesLists",ztreeNodesLists);

        /*List<NetNodeEntity> netNodeEntityList = ztreeServiceImpl.getNodesByLocationId(21);
        ZtreeBuilder ztreeBuilder = new ZtreeBuilder();
        List<ZtreeNodeEntity> beforeZtree = ztreeBuilder.beforeBuild(netNodeEntityList, 21);
        List<ZtreeNodeEntity> ztreeNodesList = ztreeBuilder.bulid(beforeZtree);
        return R.ok().put("ztreeNodesList",ztreeNodesList);*/
    }

    @RequestMapping(value = "/location/save", method = RequestMethod.POST, produces = "application/json")
    public R saveNode(@RequestBody NetNodeEntity ne) {
        ztreeServiceImpl.add(ne);
        return R.ok();
    }

    @RequestMapping(value = "/location/update", method = RequestMethod.POST, produces = "application/json")
    public R udpateNode(@PathVariable String id, @RequestBody NetNodeEntity ne) {
        ztreeServiceImpl.update(ne, id);
        return R.ok();
    }
}
