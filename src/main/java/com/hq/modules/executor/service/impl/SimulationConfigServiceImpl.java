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
import java.io.IOException;
import java.util.*;

@Service
public class SimulationConfigServiceImpl extends AbstractApiService implements SimulationConfigService {
    private static Logger logger = LoggerFactory.getLogger(SimulationConfigServiceImpl.class);

    private static final String LOCAL_SIMULATION_CONFIG_FILE_BASE_DIR = "/Users/shaw/Desktop/Simulation/simlulation_config_data/";  //仿真配置文件目录
    private static final String SERVER_SIMULATION_CONFIG_FILE_BASE_DIR = "/home/simulate801/simulation_config/";  //服务器仿真配置文件目录
    private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private TransformerFactory tff = TransformerFactory.newInstance();

    @PostConstruct
    public void init() {
        this.setUri("task/config");
    }


    @Override
    public void uploadSimulationConfig(MultipartFile roadConfigFile, MultipartFile rouConfigFile, String configName) {
        logger.info("=========== 上传config file : {} ", configName );
        boolean isGreenBand = checkGreenBand(roadConfigFile.getOriginalFilename(), rouConfigFile.getOriginalFilename());
        String roadFilePath = LOCAL_SIMULATION_CONFIG_FILE_BASE_DIR + roadConfigFile.getOriginalFilename();
        String rouFilePath = LOCAL_SIMULATION_CONFIG_FILE_BASE_DIR  + rouConfigFile.getOriginalFilename();
        File roadFile = new File(roadFilePath);
        File rouFile = new File(rouFilePath);
        if (!roadFile.getParentFile().exists()) {
            roadFile.getParentFile().mkdir();
        }
        try {
            roadConfigFile.transferTo(roadFile);
            rouConfigFile.transferTo(rouFile);
            if (isGreenBand) {
                this.uploadToServer(roadFile, rouFile, new File(LOCAL_SIMULATION_CONFIG_FILE_BASE_DIR + configName));
                this.addConfigToDataBase(configName, true);
            } else {
                this.generateConfig(configName, roadConfigFile.getOriginalFilename(), rouConfigFile.getOriginalFilename());
                this.addConfigToDataBase(configName);
            }
        } catch (Exception e) {
            logger.error("保存仿真配置文件出错！" + e);
        }
    }

    private void generateConfig(String configName, String roadName, String rouName) {
        File configFile = new File(LOCAL_SIMULATION_CONFIG_FILE_BASE_DIR + configName);
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

    private void addConfigToDataBase(String configName, boolean isGreenBand) {
        SimulationConfig simulationConfig = new SimulationConfig();
        simulationConfig.setFileName(configName);
        simulationConfig.setAddTime(new Date());
        super.add(simulationConfig);
    }

    private void uploadToServer(File roadFile, File rouFile, File configFile) {
        //no ftp
        String bashCommand1 = "echo 801 | scp /Users/shaw/Desktop/Simulation/simlulation_config_data/" + roadFile + "simulate801@10.28.171.13:/home/simulate801/simulation_config/";
        String bashCommand2 = "scp /Users/shaw/Desktop/Simulation/simlulation_config_data/" + rouFile + "simulate801@10.28.171.13:/home/simulate801/simulation_config/";
        String bashCommand3 = "scp /Users/shaw/Desktop/Simulation/simlulation_config_data/" + configFile + "simulate801@10.28.171.13:/home/simulate801/simulation_config/";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(bashCommand1);
            runtime.exec(bashCommand2);
            runtime.exec(bashCommand3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkGreenBand(String file1, String file2) {
        for (int i = 0; i < file1.length(); i++) {
            if (file1.substring(i).startsWith("greenband")) return true;
        }
        for (int i = 0; i < file2.length(); i++) {
            if (file2.substring(i).startsWith("greenband")) return true;
        }
        return false;
    }

    @Override
    public void parseRoadNetConfig(String roadNetConfigFile) {

    }

    @Override
    public void parseRouConfig(String rouNetConfigFile) {

    }

    @Override
    public String getConfigFilePath(String id, String isGreenBand) {
        SimulationConfig simulationConfig = (SimulationConfig) super.get(id);
        String configFilePath = "";
        if (isGreenBand == null ||isGreenBand.length() == 0) {
            configFilePath = LOCAL_SIMULATION_CONFIG_FILE_BASE_DIR + simulationConfig.getFileName();
        } else {
            configFilePath = SERVER_SIMULATION_CONFIG_FILE_BASE_DIR + simulationConfig.getFileName();
        }
        return configFilePath;
    }
}