package com.hq.modules.executor.service.impl;


import com.hq.modules.executor.service.RestfulApiService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TrafficRoadApiService extends AbstractApiService implements RestfulApiService {

    @PostConstruct
    public void init() {
        this.setUri("link");
    }


}
