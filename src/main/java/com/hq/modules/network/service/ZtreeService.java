package com.hq.modules.network.service;

import com.hq.modules.network.entity.NetNodeEntity;

import java.util.List;

public interface ZtreeService {
    List<NetNodeEntity> getNodesByLocationId(int location_id);
}
