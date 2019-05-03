package com.hq.modules.executor.controller;

import com.alibaba.fastjson.JSON;
import com.hq.common.utils.R;
import com.hq.modules.executor.service.impl.ValidateApiService;
import com.shaw.common.model.ReturnT;
import com.shaw.common.model.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TrafficPlanValidateController {

    @Autowired
    ValidateApiService validateApiService;


    @RequestMapping(value = "/validate/{id}", method = RequestMethod.GET, produces = "application/json")
    public R validateSingle(@PathVariable String id) {

        return R.ok().put("res", null);
    }

    @RequestMapping(value = "/validate/multiple", method = RequestMethod.POST, produces = "application/json")
    public R validateMultiple(@RequestBody String planIdListStr) {
        List<String> planIdList = JSON.parseArray(planIdListStr, String.class);
        Map<String, Object> flowData = validateApiService.validateMultiple(planIdList);

        return R.ok().put("flow", flowData);
    }


}
