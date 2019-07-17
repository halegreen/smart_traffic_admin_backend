package com.hq.modules.network.service;

import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    int addLocations(Element myroot);
    List<Integer> selectLocations();
}
