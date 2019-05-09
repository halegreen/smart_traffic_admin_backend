package com.hq.modules.executor.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public interface SimulationConfigService {

    /**
     * 上传路网配置文件和车流配置文件
     * @param roadConfigFile
     * @param rouConfigFile
     */
    void uploadSimulationConfig(MultipartFile roadConfigFile, MultipartFile rouConfigFile, String configName);


    void parseRoadNetConfig(String roadNetConfigFile);

    void parseRouConfig(String rouNetConfigFile);

    Map<String, Object> getPageList(int page, int pageSize);

    String getConfigFilePath(String id);
}
