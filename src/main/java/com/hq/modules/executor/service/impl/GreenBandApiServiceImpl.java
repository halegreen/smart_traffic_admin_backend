package com.hq.modules.executor.service.impl;

import com.alibaba.fastjson.JSON;
import com.hq.common.utils.HttpUtils;
import com.hq.modules.executor.service.GreenBandApiService;
import com.shaw.common.model.TaskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GreenBandApiServiceImpl implements GreenBandApiService {
    private static Logger logger = LoggerFactory.getLogger(GreenBandApiServiceImpl.class);

    @Autowired
    TaskApiService apiService;

    @Override
    public void runTask(String id) {
        TaskInfo taskInfo = apiService.get(id);
        String url = API_BASE_URL + "/greenband";
        logger.info("=========== 执行滤波带算法, 请求api接口：{}", url);
        try {
            Map<String, String> httpParam = new HashMap<>();
            Map<String, String> paramMap = parseTaskExecuteParam(taskInfo.getExecutorParam());
            logger.info("taskId = {}, paramMap={}", id, paramMap.toString());
            httpParam.put("configFilePath", paramMap.get("configFilePath"));
            String result = HttpUtils.doGet(url, httpParam);
            taskInfo.setTaskStatus("2");
            taskInfo.setTaskExecuteResult(result);
            taskInfo.setUpdateTime(new Date());
            apiService.update(taskInfo, id);
        } catch (Exception e) {
            logger.error("滤波带api出错" + e.getMessage());
            e.printStackTrace();
        }
    }

    private Map<String, String> parseTaskExecuteParam(String param) {
        return JSON.parseObject(param, Map.class);
    }
}
