package com.wangbei.controller;

import com.wangbei.util.PayEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by yuyidi on 2017/6/27.
 * @desc
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String welcome(PayEnum payEnum){
        return payEnum.getPayment();
    }
}
