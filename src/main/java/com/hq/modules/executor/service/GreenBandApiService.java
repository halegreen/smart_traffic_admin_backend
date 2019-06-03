package com.hq.modules.executor.service;

import org.springframework.stereotype.Service;

@Service
public interface GreenBandApiService {

    String API_BASE_URL = "http://localhost:8088/";  //api接口

    void runTask(String id);

}
