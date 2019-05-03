package com.hq.modules.executor.controller;

import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.executor.service.impl.TrafficLightApiService;
import com.shaw.common.model.TrafficLightModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TrafficLightPlanController {

    @Autowired
    TrafficLightApiService apiService;


    @RequestMapping(value = "/trafficlight/list", method = RequestMethod.GET, produces = "application/json")
    public R getAllOptimizeTask(@RequestParam(required = false, defaultValue = "0") int page,
                                @RequestParam(required = false, defaultValue = "10") int size) {

        Map<String, Object> taskPage= apiService.getPageList(page, size);

        PageUtils res = new PageUtils((List<?>) taskPage.get("pageList"), (int) taskPage.get("totalCount"), size, page);

        return R.ok().put("page", res);
    }

    @RequestMapping(value = "/trafficlight/info/{id}", method = RequestMethod.GET, produces = "application/json")
    public R getTask(@PathVariable String id) {
        TrafficLightModel taskInfo = (TrafficLightModel) apiService.get(id);
        return R.ok().put("task", taskInfo);
    }

    @RequestMapping(value = "/trafficlight/save", method = RequestMethod.POST, produces = "application/json")
    public R saveTask(@RequestBody TrafficLightModel taskInfo) {
        apiService.add(taskInfo);
        return R.ok();
    }

    @RequestMapping(value = "/trafficlight/update", method = RequestMethod.POST, produces = "application/json")
    public R udpateTask(@PathVariable String id, @RequestBody TrafficLightModel taskInfo) {
        apiService.update(taskInfo, id);
        return R.ok();
    }

    @RequestMapping(value = "/trafficlight/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public R delete(@PathVariable String id) {
        apiService.delete(id);
        return R.ok();
    }

}
