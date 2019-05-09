package com.hq.modules.executor.service.impl;

import com.alibaba.fastjson.JSON;
import com.hq.common.utils.HttpResult;
import com.hq.common.utils.HttpUtils;
import com.hq.modules.executor.service.RestfulApiService;
import com.shaw.common.model.TaskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskApiService extends AbstractApiService implements RestfulApiService {

    private static Logger logger = LoggerFactory.getLogger(TaskApiService.class);

    private static final String SUMO_BIN_PATH = "/Users/shaw/Documents/sumo-0_32_0/bin/sumo";

    @PostConstruct
    public void init() {
        this.setUri("task");
    }

    @Override
    public TaskInfo get(String id) {
        return (TaskInfo) super.get(id);
    }

    public void add(TaskInfo info) {
        TaskInfo taskInfo = new TaskInfo();
        try {
            Map<String, String> paramMap = new HashMap<>();  //仿真的ExecutorParam
            if (info.getExecutorParam() == null || info.getExecutorParam().length() == 0) {
                logger.error("========== 输入非法，参数为空！");
                return;
            }
            String[] params = info.getExecutorParam().split(",");
            for (String param : params) {
                String key = param.split("=")[0].trim();
                String value = param.split("=")[1].trim();
                paramMap.put(key, value);
            }
            if (info.getHandlerName().equals("Simulation")) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String startTime = formatter.format(new Date());
                paramMap.put("startTime", startTime);
                paramMap.put("sumoPath", SUMO_BIN_PATH);
            }
            String paramStr = JSON.toJSONString(paramMap);
            logger.info("============ 解析出的任务执行参数 = {}", paramStr);
            Date date = new Date();
            taskInfo.setAddTime(date);
            taskInfo.setUpdateTime(date);
            taskInfo.setHandlerName(info.getHandlerName());
            taskInfo.setExecutorParam(paramStr);
            taskInfo.setExecutorFailRetryCount(info.getExecutorFailRetryCount());
            taskInfo.setExecutorTimeout(info.getExecutorTimeout());
            taskInfo.setTaskStatus("0");
            super.add(taskInfo);
        } catch (Exception e) {
            logger.error("============ 解析任务参数出错！", e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(TaskInfo info, String id) {
        super.update(info, id);
    }


    @Override
    public void runBatchTask(List<String> taskIdList) {
        if (taskIdList == null || taskIdList.size() == 0) {
            logger.error("========== task list size == 0");
        }
        String url = API_BASE_URL + uri + "/submit";
        logger.info("========== 请求api接口：{}", url);
        try {
            String taskIdListStr = JSON.toJSONString(taskIdList);
            HttpResult result = HttpUtils.doPostJson(url, taskIdListStr);
            logger.info("=========== api result = {}", result.getBody());
        } catch (Exception e) {
            logger.error("=========== 批量运行任务失败");
        }


    }
}
