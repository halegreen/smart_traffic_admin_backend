package com.hq.modules.network.service;

import org.dom4j.Element;
import org.springframework.stereotype.Service;

@Service
public interface LaneService {
    String addLanes(Element myroot);
}
