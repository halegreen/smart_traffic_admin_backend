package com.hq.modules.network.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrafficLightService {

    List<String> getPhaseIdList(String trafficId);
}
