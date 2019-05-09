package com.hq.modules.executor.service.impl;

import com.alibaba.fastjson.JSON;
import com.hq.common.utils.HttpResult;
import com.hq.common.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidateApiService {

    private static Logger logger = LoggerFactory.getLogger(ValidateApiService.class);

    private static final String API_BASE_URL = "http://localhost:8088/api/v1/validate";
    private static final List<String> status = Arrays.asList("流量", "车流占据道路的时间比率", "平均行程速度", "平均排队长度", "平均排队等待时间");
    private static final List<String> new_status = Arrays.asList("volumeQ", "thetaT", "velocityV", "avgQueueLength", "avgQueueTime");


    /**
     * 返回主要是需要转换成echarts的格式
     * @param planIds
     * @return
     */
    public Map<String, List<String>> validateMultiple(List<String> planIds) {
        if (planIds == null || planIds.size() == 0) {
            logger.error("========== task list size == 0");
        }
        String url = API_BASE_URL + "/multiple";
        logger.info("========== 请求api接口：{}", url);
        try {
            String planIdListStr = JSON.toJSONString(planIds);
            HttpResult result = HttpUtils.doPostJson(url, planIdListStr);
            logger.info("=========== api result = {}", result.getBody());
            Map<String, List<String>> map = JSON.parseObject(result.getBody(), Map.class);
            logger.info("=========== flow map = {}", map.toString());
            Map<String, List<String>> ret =  new HashMap<>();
            for (String key : map.keySet()) {
                List<String> val = map.get(key);
                if (key.equals("流量")) {
                    ret.put("volumeQ", val);
                } else if (key.equals("车流占据道路的时间比率")) {
                    ret.put("thetaT", val);
                } else if (key.equals("平均行程速度")) {
                    ret.put("velocityV", val);
                } else if (key.equals("平均排队长度")) {
                    ret.put("avgQueueLength", val);
                } else if (key.equals("平均排队等待时间")) {
                    ret.put("avgQueueTime", val);
                }
            }
            logger.info("=========== flow ret = {}", ret.toString());
            return ret;
        } catch (Exception e) {
            logger.error("=========== 批量验证失败", e);
            e.printStackTrace();
            return null;
        }

    }

}
