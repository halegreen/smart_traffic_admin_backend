package com.hq.modules.executor.controller;

import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.executor.service.impl.TrafficFlowApiService;
import com.shaw.common.model.TrafficLightModel;
import com.shaw.common.model.TrafficStateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TrafficFlowController {

    @Autowired
    TrafficFlowApiService apiService;


    @RequestMapping(value = "/flow/list", method = RequestMethod.GET, produces = "application/json")
    public R getAllOptimizeTask(@RequestParam(required = false, defaultValue = "0") int page,
                                @RequestParam(required = false, defaultValue = "10") int size) {

        Map<String, Object> taskPage= apiService.getPageList(page, size);

        PageUtils res = new PageUtils((List<?>) taskPage.get("pageList"), (int) taskPage.get("totalCount"), size, page);

        return R.ok().put("page", res);
    }

    @RequestMapping(value = "/flow/search", method = RequestMethod.GET, produces = "application/json")
    public R search(@RequestParam String linkId,
                    @RequestParam String startTime, @RequestParam String endTime) {

        Map<String, String> params = new HashMap<>();
        params.put("linkId", linkId);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        Map<String, Object> taskPage= apiService.search(params);

        PageUtils res = new PageUtils((List<?>) taskPage.get("pageList"),
                (int) taskPage.get("totalCount"), ((List<?>) taskPage.get("pageList")).size(), 0);

        return R.ok().put("page", res);
    }


    @RequestMapping(value = "/flow/linechart", method = RequestMethod.GET, produces = "application/json")
    public R flowStatistics(@RequestParam String linkId,
                            @RequestParam String startTime, @RequestParam String endTime) {
        Map<String, String> params = new HashMap<>();
        params.put("linkId", linkId);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        Map<String, Object> taskPage = apiService.getStatisticData(params);
        List<Map<String, Object>> ret = new ArrayList<>();
        for (String key : taskPage.keySet()) {
            if (key.equals("timeSeries")) continue;
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("name", key);
            tmp.put("dataList", taskPage.get(key));
            ret.add(tmp);
        }
        return R.ok().put("page", ret).put("length", ret.size()).put("timeSeries",  taskPage.get("timeSeries"));
    }


    @RequestMapping(value = "/flow/info/{id}", method = RequestMethod.GET, produces = "application/json")
    public R getTask(@PathVariable String id) {
        TrafficStateData taskInfo = (TrafficStateData) apiService.get(id);
        return R.ok().put("task", taskInfo);
    }

    @RequestMapping(value = "/flow/save", method = RequestMethod.POST, produces = "application/json")
    public R saveTask(@RequestBody TrafficStateData taskInfo) {
        apiService.add(taskInfo);
        return R.ok();
    }

    @RequestMapping(value = "/flow/update", method = RequestMethod.POST, produces = "application/json")
    public R udpateTask(@PathVariable String id, @RequestBody TrafficStateData taskInfo) {
        apiService.update(taskInfo, id);
        return R.ok();
    }

    @RequestMapping(value = "/flow/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public R delete(@PathVariable String id) {
        apiService.delete(id);
        return R.ok();
    }

    /**
     * 导入外部流量数据，并进行格式的转化
     * @param flowFile
     * @return
     */
    @RequestMapping(value = "/flow/upload", method = RequestMethod.POST, produces = "application/json")
    public R uploadOtherFlowData(@RequestParam MultipartFile flowFile) {
        if (!flowFile.isEmpty()) {
           apiService.uploadFlow(flowFile);
        }
        return R.ok();
    }

    /**
     * 流量聚类分析：可用于路口信号灯方案的时段划分依据，发现模式相同的流量特征，这类流量可用相近的信号灯方案
     * @param linkId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/flow/cluster", method = RequestMethod.GET, produces = "application/json")
    public R flowClusterAnalysis(@RequestParam String linkId,
                            @RequestParam String startTime, @RequestParam String endTime) {

        return R.ok();
    }

}
