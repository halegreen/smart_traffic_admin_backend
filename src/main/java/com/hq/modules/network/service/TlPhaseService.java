package com.hq.modules.network.service;

import org.dom4j.Element;
import org.springframework.stereotype.Service;

@Service
public interface TlPhaseService {
    String addTlPhases(Element myroot);
}
