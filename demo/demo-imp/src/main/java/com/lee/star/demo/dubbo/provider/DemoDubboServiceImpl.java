package com.lee.star.demo.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.lee.star.dubbo.api.DemoDubboService;
import org.springframework.stereotype.Component;

@Service
@Component
public class DemoDubboServiceImpl implements DemoDubboService {

    @Override
    public String demoForDubbo(String aa) {
        return aa;
    }
}
