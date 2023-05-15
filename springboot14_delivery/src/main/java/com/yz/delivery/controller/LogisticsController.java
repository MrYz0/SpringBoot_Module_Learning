package com.yz.delivery.controller;

import com.yz.delivery.utils.LogisticsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "aLi-delivery", description = "")
@RestController
public class LogisticsController {

    @ApiOperation("物流详情")
    @GetMapping("/list")
    public String list(String type,String shipNumber) throws Exception {
        String logistics = LogisticsUtils.getLogistics(type, shipNumber);
        //返回json格式
        return logistics;
    }
}