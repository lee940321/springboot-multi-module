package com.lee.star.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时任务测试类
 * @author: lidanfeng
 * @create: 2019/3/4 16:43
 */
@Component
@Slf4j
public class HeartbeatJob  implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("#############  I'm waking!!! #############");
    }
}
