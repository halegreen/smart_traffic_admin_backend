package com.hq.modules.executor.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RestfulApiService {

    Object get(String id);

    void add(Object info);

    void update(Object info, String id);

    void delete(String id);

    Map<String, Object> getPageList(int page, int pageSize);

    Map<String, Object> getPageList(int page, int pageSize, String param);

}
