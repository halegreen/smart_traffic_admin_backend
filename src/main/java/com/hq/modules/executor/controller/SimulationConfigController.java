package com.hq.modules.executor.controller;


import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.executor.service.SimulationConfigService;
import com.hq.modules.executor.service.impl.TaskApiService;
import com.shaw.common.model.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
public class SimulationConfigController {

    @Autowired
    SimulationConfigService simulationConfigService;

    /**
     * 上传仿真配置，包括路网文件和车流文件
     * @param roadFile
     * @param rouFile
     * @return
     */
    @RequestMapping(value = "/config/upload", method = RequestMethod.POST, produces = "application/json")
    public R uploadSimulationConfig(@RequestParam MultipartFile roadFile,
                                    @RequestParam MultipartFile rouFile ) {
        if (!roadFile.isEmpty() && !rouFile.isEmpty()) {
            simulationConfigService.uploadSimulationConfig(roadFile, rouFile,
                    roadFile.getOriginalFilename() + "_" + rouFile.getOriginalFilename());
        }
        return R.ok();
    }

    /**
     * 获取所有仿真配置的列表
     * @return
     */
    @RequestMapping(value = "/config/list", method = RequestMethod.GET, produces = "application/json")
    public R getSimulationList(@RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "10") int size) {
        Map<String, Object> taskPage = simulationConfigService.getPageList(page, size);
        PageUtils res = new PageUtils((List<?>) taskPage.get("pageList"), (int) taskPage.get("totalCount"), size, page);
        return R.ok().put("page", res);
    }

    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET, produces = "application/json")
    public R getConfigPath(@PathVariable String id) {
        String path = simulationConfigService.getConfigFilePath(id);
        return R.ok().put("configFilePath", path);
    }
}
