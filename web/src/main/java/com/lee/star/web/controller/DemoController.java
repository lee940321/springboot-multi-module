package com.lee.star.web.controller;

import com.lee.star.demo.service.DemoService;
import com.lee.star.demo.pojo.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author: lidanfeng
 * @create: 2019/3/1 10:50
 */
@RequestMapping("/demo")
@RestController
public class DemoController {

    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @GetMapping("/list")
    public List<Demo> getList() {
        return demoService.getList();
    }
}
