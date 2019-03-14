package com.lee.star.demo.service;

import com.lee.star.demo.dao.DemoMapper;
import com.lee.star.demo.pojo.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description: demo 业务实现类
 * @author: lidanfeng
 * @create: 2019/3/1 10:42
 */
@Service
public class DemoServiceImpl implements DemoService {

    Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Demo> getList() {
        return demoMapper.selectAll();
    }
}
