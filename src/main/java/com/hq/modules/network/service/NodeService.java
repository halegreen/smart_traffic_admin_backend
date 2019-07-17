package com.hq.modules.network.service;

import com.hq.modules.network.entity.NetNodeEntity;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NodeService {

    String addNodes(Element myroot,int location_id);
    }