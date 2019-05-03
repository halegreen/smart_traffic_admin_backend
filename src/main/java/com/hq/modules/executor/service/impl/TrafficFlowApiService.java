package com.hq.modules.executor.service.impl;

import com.alibaba.fastjson.JSON;
import com.hq.common.utils.HttpUtils;
import com.hq.modules.executor.service.RestfulApiService;
import com.shaw.common.model.TrafficStateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrafficFlowApiService extends AbstractApiService implements RestfulApiService {

    private static Logger logger = LoggerFactory.getLogger(TrafficFlowApiService.class);

    @PostConstruct
    public void init() {
        this.setUri("flow");
    }


    @Override
    public Map<String, Object> search(Map<String, String> params) {
        String url = API_BASE_URL + uri + "/search";
        logger.info("========== 请求api接口：{}", url);

        Map<String,Object> ret = new HashMap<>();
        try {
            String result = HttpUtils.doGet(url, params);
            logger.info("======== api res = {}" , result);

            Map<String, Object> tmp = JSON.parseObject(result, Map.class);
            ret.put("totalCount", tmp.get("totalCount"));
            String infoStr = (String) tmp.get("pageList");
            List<Object> retInfos = JSON.parseArray(infoStr, classMapping.get(uri));
            ret.put("pageList", retInfos);
        } catch (Exception e) {
            logger.error("api search信息出错" + e.getMessage());
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Map<String, Object> getStatisticData(Map<String, String> params) {
        String url = API_BASE_URL + uri + "/search";  //获取折线图数据
        logger.info("========== 请求api接口：{}", url);

        Map<String, Object> ret = new HashMap<>(); //折线图数据格式：key:交通状态特征，value:不同时间点的list
        ret.put("流量", new ArrayList<>());
        ret.put("车流占据道路的时间比率",  new ArrayList<>());
        ret.put("平均行程速度",  new ArrayList<>());
        ret.put("平均排队长度",  new ArrayList<>());
        ret.put("平均排队等待时间",  new ArrayList<>());

        try {
            String result = HttpUtils.doGet(url, params);
            logger.info("======== api res = {}" , result);

            Map<String, Object> tmp = JSON.parseObject(result, Map.class);

            String infoStr = (String) tmp.get("pageList");
            List<Object> flowData = JSON.parseArray(infoStr, classMapping.get(uri));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String> timeList = flowData.stream().map(x -> formatter.format( ((TrafficStateData)x).getTimeStamp())).collect(Collectors.toList());
            ret.put("timeSeries", timeList);

            for (String key : ret.keySet()) {
                if (key.equals("timeSeries")) continue;
                List<String> timeSeries = new ArrayList<>();
                for (Object data : flowData) {
                    String val = null;
                    if (key.equals("流量")) {
                        val = String.valueOf(((TrafficStateData) data).getVolumeQ());
                    } else if (key.equals("车流占据道路的时间比率")) {
                        val = String.valueOf(((TrafficStateData) data).getThetaT());
                    } else if (key.equals("平均行程速度")) {
                        val = String.valueOf(((TrafficStateData) data).getVelocityV());
                    } else if (key.equals("平均排队长度")) {
                        val = String.valueOf(((TrafficStateData) data).getAvgQueueLength());
                    } else if (key.equals("平均排队等待时间")) {
                        val = String.valueOf(((TrafficStateData) data).getAvgQueueTime());
                    }
                    timeSeries.add(val);
                }
                ret.put(key, timeSeries);
            }
            logger.info("========= 折线图数据：= {}", JSON.toJSONString(ret));
        } catch (Exception e) {
            logger.error("api search信息出错" + e.getMessage());
            e.printStackTrace();
        }

        return ret;

    }
}
