package com.hq.modules.executor.service.impl;

import com.alibaba.fastjson.JSON;
import com.hq.modules.executor.service.SimulationConfigService;
import com.shaw.common.model.SimulationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimulationConfigServiceImpl extends AbstractApiService implements SimulationConfigService {
    private static Logger logger = LoggerFactory.getLogger(SimulationConfigServiceImpl.class);

    private static final String SIMULATION_CONFIG_FILE_BASE_DIR = "/Users/shaw/Desktop/Simulation/simlulation_config_data/";  //仿真配置文件目录
    private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private TransformerFactory tff = TransformerFactory.newInstance();

    @PostConstruct
    public void init() {
        this.setUri("task/config");
    }


    @Override
    public void uploadSimulationConfig(MultipartFile roadConfigFile, MultipartFile rouConfigFile, String configName) {
        logger.info("=========== 上传config file : {} ", configName );

        String roadFilePath = SIMULATION_CONFIG_FILE_BASE_DIR + roadConfigFile.getOriginalFilename();
        String rouFilePath = SIMULATION_CONFIG_FILE_BASE_DIR  + rouConfigFile.getOriginalFilename();
        File roadFile = new File(roadFilePath);
        File rouFile = new File(rouFilePath);
        if (!roadFile.getParentFile().exists()) {
            roadFile.getParentFile().mkdir();
        }
        try {
            roadConfigFile.transferTo(roadFile);
            rouConfigFile.transferTo(rouFile);
            this.generateConfig(configName, roadConfigFile.getOriginalFilename(), rouConfigFile.getOriginalFilename());
            this.addConfigToDataBase(configName);
        } catch (Exception e) {
            logger.error("保存仿真配置文件出错！" + e);
        }
    }

    private void generateConfig(String configName, String roadName, String rouName) {
        File configFile = new File(SIMULATION_CONFIG_FILE_BASE_DIR + configName);
        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlStandalone(true);
            Element configuration = document.createElement("configuration");
            configuration.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            configuration.setAttribute("xsi:noNamespaceSchemaLocation", "http://sumo.dlr.de/xsd/sumoConfiguration.xsd");
            document.appendChild(configuration);
            Element input = document.createElement("input");
            Element netFile = document.createElement("net-file");
            netFile.setAttribute("value", roadName);
            Element rouFile = document.createElement("route-files");
            rouFile.setAttribute("value", rouName);
            configuration.appendChild(input);
            input.appendChild(netFile);
            input.appendChild(rouFile);
            Transformer transformer = tff.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(configFile));
        } catch (Exception e) {
            logger.error("生成仿真配置文件出错"+ e);
        }
    }


    private void addConfigToDataBase(String configName) {
        SimulationConfig simulationConfig = new SimulationConfig();
        simulationConfig.setFileName(configName);
        simulationConfig.setAddTime(new Date());
        super.add(simulationConfig);
    }

    @Override
    public void parseRoadNetConfig(String roadNetConfigFile) {

    }

    @Override
    public void parseRouConfig(String rouNetConfigFile) {

    }

    @Override
    public String getConfigFilePath(String id) {
        SimulationConfig simulationConfig = (SimulationConfig) super.get(id);
        String configFilePath = SIMULATION_CONFIG_FILE_BASE_DIR + simulationConfig.getFileName();
        return configFilePath;
    }
}