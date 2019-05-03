package com.hq.modules.executor.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApiServiceImpl extends AbstractApiService {

    private static Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    public ApiServiceImpl() {
        this.init();
    }

    public void init() {
        uri = methodMapping.get(getEntityClass());
        logger.info("============== 当前Service的 uri = {}， 当前的entity class = {}", uri, getEntityClass());
    }


    public void runBatchTask(List<String> taskIdList) {
        String url = API_BASE_URL + uri + "/submit";

    }

}
