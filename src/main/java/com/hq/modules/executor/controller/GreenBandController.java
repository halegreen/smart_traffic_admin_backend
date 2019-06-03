package com.hq.modules.executor.controller;

import com.hq.common.utils.R;
import com.hq.modules.executor.service.GreenBandApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreenBandController {

    @Autowired
    GreenBandApiService greenBandService;

    @RequestMapping(value = "/greenband/run", method = RequestMethod.GET, produces = "application/json")
    public R runBatchTask(@PathVariable String id){
        greenBandService.runTask(id);
        return R.ok();
    }
}
