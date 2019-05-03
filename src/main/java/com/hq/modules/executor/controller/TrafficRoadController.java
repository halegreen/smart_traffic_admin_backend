package com.hq.modules.executor.controller;

import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.executor.service.impl.TrafficFlowApiService;
import com.hq.modules.executor.service.impl.TrafficRoadApiService;
import com.shaw.common.model.RoadLink;
import com.shaw.common.model.TrafficStateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TrafficRoadController {


    @Autowired
    TrafficRoadApiService apiService;


    @RequestMapping(value = "/road/list", method = RequestMethod.GET, produces = "application/json")
    public R getAllOptimizeTask(@RequestParam(required = false, defaultValue = "0") int page,
                                @RequestParam(required = false, defaultValue = "10") int size) {

        Map<String, Object> taskPage= apiService.getPageList(page, size);

        PageUtils res = new PageUtils((List<?>) taskPage.get("pageList"), (int) taskPage.get("totalCount"), size, page);

        return R.ok().put("page", res);
    }

    @RequestMapping(value = "/road/all", method = RequestMethod.GET, produces = "application/json")
    public R getAllRoad() {
        Map<String, Object> taskPage= apiService.getAll();
        PageUtils res = new PageUtils((List<?>) taskPage.get("pageList"));
        return R.ok().put("page", res);
    }

    @RequestMapping(value = "/road/info/{id}", method = RequestMethod.GET, produces = "application/json")
    public R getTask(@PathVariable String id) {
        RoadLink taskInfo = (RoadLink) apiService.get(id);
        return R.ok().put("task", taskInfo);
    }

    @RequestMapping(value = "/road/save", method = RequestMethod.POST, produces = "application/json")
    public R saveTask(@RequestBody RoadLink taskInfo) {
        apiService.add(taskInfo);
        return R.ok();
    }

    @RequestMapping(value = "/road/update", method = RequestMethod.POST, produces = "application/json")
    public R udpateTask(@PathVariable String id, @RequestBody RoadLink taskInfo) {
        apiService.update(taskInfo, id);
        return R.ok();
    }

    @RequestMapping(value = "/road/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public R delete(@PathVariable String id) {
        apiService.delete(id);
        return R.ok();
    }
}
