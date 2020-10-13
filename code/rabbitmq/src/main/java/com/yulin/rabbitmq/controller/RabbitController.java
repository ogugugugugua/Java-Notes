package com.yulin.rabbitmq.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.yulin.rabbitmq.common.CommonResult;
import com.yulin.rabbitmq.simple.SimpleSender;
import com.yulin.rabbitmq.work.WorkSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "RabbitController", description = "RabbitMQ功能测试")
@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private WorkSender workSender;


    @ApiOperation("简单模式")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult simpleTest() {
        for(int i=0;i<1000;i++){
            simpleSender.send();
//            ThreadUtil.sleep(500);
        }
        return CommonResult.success(null);
    }

    @RequestMapping("/work")
    @ResponseBody
    public CommonResult testWork() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
        }
        return CommonResult.success(null);
    }
}