package com.lee.star.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis config
 */
@Configuration
public class RedisConfiguration {


    /******************** 配置redis--Start   *******************/
    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.pool.max-active}") int maxTotal,
                                           @Value("${spring.redis.pool.max-idle}") int maxIdle,
                                           @Value("${spring.redis.pool.max-wait}") int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }

    @Bean
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,
                               @Value("${spring.redis.host}") String host,
                               @Value("${spring.redis.port}") int port,
                               @Value("${spring.redis.password}") String password,
                               @Value("${spring.redis.timeout}") int timeout,
                               @Value("${spring.redis.database}") int database) {
        if (StringUtils.isBlank(password)) {
            password = null;
        }
        return new JedisPool(config, host, port, timeout, password, database);
    }
}
