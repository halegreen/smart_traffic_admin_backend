package com.hq.modules.executor.service.impl;

import com.alibaba.fastjson.JSON;
import com.hq.common.utils.GenericSuperclassUtil;
import com.hq.common.utils.HttpResult;
import com.hq.common.utils.HttpUtils;
import com.shaw.common.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public abstract class AbstractApiService {

    private static Logger logger = LoggerFactory.getLogger(AbstractApiService.class);

    protected static final String API_BASE_URL = "http://localhost:8088/api/v1/";  //api接口
    protected String uri; //对应接口类型uri

    protected static final Map<String, Class> classMapping = new HashMap<>();
    protected static final Map<Class, String> methodMapping = new HashMap<>();

    static {
        classMapping.put("task", TaskInfo.class);
        classMapping.put("plan", TrafficLightModel.class);
        classMapping.put("flow", TrafficStateData.class);
        classMapping.put("link", RoadLink.class);
        classMapping.put("task/config", SimulationConfig.class);

        methodMapping.put(TaskInfo.class, "task");
        methodMapping.put(TrafficLightModel.class, "plan");
        methodMapping.put(TrafficStateData.class, "flow");
        methodMapping.put(RoadLink.class, "link");
        methodMapping.put(SimulationConfig.class, "task/config");
    }


    public Object get(String id) {
        String url = API_BASE_URL + uri + "/get" + "/" + id;
        logger.info("========== 请求api接口：{}", url);
        Object ret = null;
        try {
            String result = HttpUtils.doGet(url);
            ret = JSON.parseObject(result, classMapping.get(uri));
            return ret;
        } catch (Exception e) {
            logger.error("api获取info信息出错" + e.getMessage());
            e.printStackTrace();
            return ret;
        }
    }

    public void add(Object info) {
        String url = API_BASE_URL + uri + "/add";
        logger.info("========== 请求api接口：{}", url);
        try {
            String jonsInfo = JSON.toJSONString(info);
            HttpResult result = HttpUtils.doPostJson(url, jonsInfo);
        } catch (Exception e) {
            logger.error("api添加info信息出错" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(Object info, String id) {
        String url = API_BASE_URL + uri + "/update" + "/" + id;
        logger.info("========== 请求api接口：{}", url);
        try {
            String jonsInfo = JSON.toJSONString(info);
            HttpResult result = HttpUtils.doPostJson(url, jonsInfo);
        } catch (Exception e) {
            logger.error("api更新 info信息出错" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        String url = API_BASE_URL + uri + "/delete";

        logger.info("========== 请求api接口：{}", url);

    }

    public Map<String, Object> getAll() {
        String url = API_BASE_URL + uri + "/all";
        logger.info("========== 请求api接口：{}", url);

        Map<String, Object> ret = new HashMap<>();
        try {
            String result = HttpUtils.doGet(url);
            Map<String, Object> tmp = JSON.parseObject(result, Map.class);
            ret.put("totalCount", tmp.get("totalCount"));
            String infosStr = (String) tmp.get("pageList");
            List<Object> retInfos = JSON.parseArray(infosStr, classMapping.get(uri));
            ret.put("pageList", retInfos);
        } catch (Exception e) {
            logger.error("api获取列表信息出错" + e.getMessage());
            e.printStackTrace();
        }
        return ret;

    }

    public Map<String, Object> getPageList(int page, int pageSize) {
        String url = API_BASE_URL + uri + "/list";
        logger.info("========== 请求api接口：{}", url);

        Map<String, String> httpParam = new HashMap<>();
        httpParam.put("page", String.valueOf(page));
        httpParam.put("size", String.valueOf(pageSize));
        Map<String,Object> ret = new HashMap<>();
        try {
            String result = HttpUtils.doGet(url, httpParam);
            Map<String, Object> tmp = JSON.parseObject(result, Map.class);
            ret.put("totalCount", tmp.get("totalCount"));
            String infosStr = (String) tmp.get("pageList");
            List<Object> retInfos = JSON.parseArray(infosStr, classMapping.get(uri));
            ret.put("pageList", retInfos);
        } catch (Exception e) {
            logger.error("api获取列表信息出错" + e.getMessage());
            e.printStackTrace();
        }
        return ret;
    }

    public Map<String, Object> getPageList(int page, int pageSize, String param) {
        String url = API_BASE_URL + uri + "/list";
        logger.info("========== 请求api接口：{}", url);

        Map<String, String> httpParam = new HashMap<>();
        httpParam.put("page", String.valueOf(page));
        httpParam.put("size", String.valueOf(pageSize));
        Map<String, Object> tmp = null;
        Map<String, Object> ret = new HashMap<>();
        try {
            String result = HttpUtils.doGet(url, httpParam);
            tmp = JSON.parseObject(result, Map.class);
            ret.put("totalCount", tmp.get("totalCount"));
            List<Object> taskInfos = new ArrayList<>();
            String taskInfosStr = (String) tmp.get("pageList");
            List<Object> retTaskInfos = JSON.parseArray(taskInfosStr, classMapping.get(uri));
            for (Object task : retTaskInfos) {
                if (this.uri.equals("task") && ((TaskInfo)task).getHandlerName().equals(param)) {
                    taskInfos.add(task);
                }
            }
            ret.put("pageList", taskInfos);
        } catch (Exception e) {
            logger.error("api获取列表信息出错" + e.getMessage());
            e.printStackTrace();
        }
        return ret;
    }


    protected void runBatchTask(List<String> taskIdList) {

    }


    protected Map<String, Object> search(Map<String, String> params) {
        return null;
    }

    protected Map<String, Object> getStatisticData(Map<String, String> params) {
        return null;
    }

    public Class<?> getEntityClass() {
        return GenericSuperclassUtil.getActualTypeArgument(this.getClass());
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
