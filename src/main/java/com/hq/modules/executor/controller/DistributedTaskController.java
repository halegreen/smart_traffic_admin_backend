package com.hq.modules.executor.controller;


import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.executor.service.impl.TaskApiService;
import com.shaw.common.model.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DistributedTaskController {

    @Autowired
    TaskApiService apiService;


    @RequestMapping(value = "/task/simulation/list", method = RequestMethod.GET, produces = "application/json")
    public R getAllSimulationTask(@RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "10") int size) {

        Map<String, Object> taskPage = apiService.getPageList(page, size, "Simulation");

        PageUtils res = new PageUtils((List<?>) taskPage.get("pageList"), (int) taskPage.get("totalCount"), size, page);

        return R.ok().put("page", res);
    }


    @RequestMapping(value = "/task/optimize/list", method = RequestMethod.GET, produces = "application/json")
    public R getAllOptimizeTask(@RequestParam(required = false, defaultValue = "0") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {

        Map<String, Object> taskPage= apiService.getPageList(page, size, "Optimize");

        PageUtils res = new PageUtils((List<?>) taskPage.get("pageList"), (int) taskPage.get("totalCount"), size, page);

        return R.ok().put("page", res);
    }

    @RequestMapping(value = "/task/info/{id}", method = RequestMethod.GET, produces = "application/json")
    public R getTask(@PathVariable String id) {
        TaskInfo taskInfo = apiService.get(id);
        return R.ok().put("task", taskInfo);
    }

    @RequestMapping(value = "/task/save", method = RequestMethod.POST, produces = "application/json")
    public R saveTask(@RequestBody TaskInfo taskInfo) {
        apiService.add(taskInfo);
        return R.ok();
    }


    @RequestMapping(value = "/task/update", method = RequestMethod.POST, produces = "application/json")
    public R udpateTask(@PathVariable String id, @RequestBody TaskInfo taskInfo) {
        apiService.update(taskInfo, id);
        return R.ok();
    }

    @RequestMapping(value = "/task/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public R deleteTrafficOptimizaTask(@PathVariable String id) {
        apiService.delete(id);
        return R.ok();
    }


    @RequestMapping(value = "/task/run", method = RequestMethod.POST, produces = "application/json")
    public R runBatchTask(@RequestBody Long[] taskIds){
        List<String> ids = new ArrayList<>();
        for (long id : taskIds) ids.add(String.valueOf(id));
        apiService.runBatchTask(ids);
        return R.ok();
    }

}
