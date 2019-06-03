package com.hq.modules.network.service.impl;

import com.hq.modules.network.service.TrafficLightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficLightServiceImpl implements TrafficLightService {

    /**
     * 根据信号灯id获取该信号灯所有的相位信息
     * @param trafficId
     * @return
     */
    @Override
    public List<String> getPhaseIdList(String trafficId) {
        return null;
    }

}
