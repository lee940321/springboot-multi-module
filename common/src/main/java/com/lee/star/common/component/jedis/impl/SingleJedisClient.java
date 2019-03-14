package com.lee.star.common.component.jedis.impl;

import com.lee.star.common.component.jedis.CallBack;
import com.lee.star.common.component.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 单机版客户端实现类
 */
@Component
public class SingleJedisClient implements JedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Value("${redis.profiles.active}")
    private String profile;

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            String string = jedis.get(profile + key);
            return string;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String string = jedis.set(profile + key, value);
            return string;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            Long dels = jedis.del(profile + key);
            return dels;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    @Override
    public String set(String key, String value, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String string;
            if (flag) {
                string = jedis.set(profile + key, value);
            } else {
                string = jedis.set(key, value);
            }
            return string;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long setnx(String key, String value, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result;
            if (flag) {
                result = jedis.setnx(profile + key, value);
            } else {
                result = jedis.setnx(key, value);
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String get(String key, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            String string = null;
            if (flag) {
                string = jedis.get(profile + key);
            } else {
                string = jedis.get(key);
            }
            return string;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long del(String key, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            Long dels;
            if (flag) {
                dels = jedis.del(profile + key);
            } else {
                dels = jedis.del(key);
            }
            return dels;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long incrby(String key, Long value, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result;
            if (flag) {
                result = jedis.incrBy(profile + key, value);
            } else {
                result = jedis.incrBy(key, value);
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hset(profile + key, field, value);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String string = jedis.hget(profile + key, field);
            return string;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hdel(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hdel(profile + key, fields);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Map<String, String> map = jedis.hgetAll(profile + key);
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long incr(String key, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result;
            if (flag) {
                result = jedis.incr(profile + key);
            } else {
                result = jedis.incr(key);
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.expire(profile + key, seconds);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long expire(String key, int seconds, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result;
            if (flag) {
                result = jedis.expire(profile + key, seconds);
            } else {
                result = jedis.expire(key, seconds);
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String setex(String key, int seconds, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.setex(profile + key, seconds, value);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String setex(String key, int seconds, String value, Boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (flag) {
                String result = jedis.setex(profile + key, seconds, value);
                return result;
            } else {
                String result = jedis.setex(key, seconds, value);
                return result;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.ttl(profile + key);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Double aDouble = jedis.hincrByFloat(profile + key, field, value);
            return aDouble;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long aLong = jedis.hincrBy(profile + key, field, value);
            return aLong;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public Long hincrBy(String key, String field, long value, Boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long aLong;
            if (flag) {
                aLong = jedis.hincrBy(profile + key, field, value);
            } else {
                aLong = jedis.hincrBy(key, field, value);
            }
            return aLong;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String msg = jedis.hmset(profile + key, map);
            return msg;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hsetnxt(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long msg = jedis.hsetnx(profile + key, field, value);
            return msg;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public Long hsetnxt(String key, String field, String value, Boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long msg;
            if (flag) {
                msg = jedis.hsetnx(profile + key, field, value);
            } else {
                msg = jedis.hsetnx(key, field, value);
            }
            return msg;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> smembers = jedis.smembers(profile + key);
            return smembers;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sadd(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long sadd = jedis.sadd(profile + key, member);
            return sadd;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long srem(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long srem = jedis.srem(profile + key, member);
            return srem;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sremArray(String key, List<String> strArr) {
        Jedis jedis = null;
        try {
            String[] strs = new String[strArr.size()];
            for (int i = 0; i < strArr.size(); i++) {
                strs[i] = strArr.get(i);
            }
            jedis = jedisPool.getResource();
            Long srem = jedis.srem(profile + key, strs);
            return srem;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set smembers(String key, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> smembers;
            if (flag) {
                smembers = jedis.smembers(profile + key);
            } else {
                smembers = jedis.smembers(key);
            }
            return smembers;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sadd(String key, String member, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long sadd;
            if (flag) {
                sadd = jedis.sadd(profile + key, member);
            } else {
                sadd = jedis.sadd(key, member);
            }
            return sadd;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long srem(String key, String member, boolean flag) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long srem;
            if (flag) {
                srem = jedis.srem(profile + key, member);
            } else {
                srem = jedis.srem(key, member);
            }
            return srem;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sremArray(String key, List<String> strArr, boolean flag) {
        Jedis jedis = null;
        try {
            String[] strs = new String[strArr.size()];
            for (int i = 0; i < strArr.size(); i++) {
                strs[i] = strArr.get(i);
            }
            jedis = jedisPool.getResource();
            Long srem;
            if (flag) {
                srem = jedis.srem(profile + key, strs);
            } else {
                srem = jedis.srem(key, strs);
            }
            return srem;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lset(String key, String strings) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long rpush = jedis.rpush(profile + key, strings);
            return rpush;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> lget(String key) {
        Jedis jedis = null;
        String keyStr = profile + key;
        try {
            List<String> lrange = null;
            jedis = jedisPool.getResource();
            if (jedis.exists(keyStr)) {
                Long llen = jedis.llen(keyStr);
                lrange = jedis.lrange(keyStr, 0, llen);
            }
            return lrange;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lrem(String key, String values) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long lrem = jedis.lrem(profile + key, 0, values);
            return lrem;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> keys = jedis.keys(profile + pattern);
            return keys;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public List<Object> batchHashMultipleSet(Map<String, Map<String, String>> multiValues) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            for (Map.Entry<String, Map<String, String>> entry : multiValues.entrySet()) {
                pipeline.hmset(profile + entry.getKey(), entry.getValue());
            }
            return pipeline.syncAndReturnAll();
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public long batchFlushHashMultipleSet(Map<String, Map<String, String>> multiValues) {
        Jedis jedis = null;
        Pipeline pipeline = null;
        try {
            jedis = jedisPool.getResource();
            pipeline = jedis.pipelined();

            //删除二级key
            for (Map.Entry<String, Map<String, String>> entry : multiValues.entrySet()) {
                Map<String, String> value = entry.getValue();
                Set<String> strings = value.keySet();
                String[] fieldKeys = new String[strings.size()];
                int i = 0;
                for (String fieldKey : strings) {
                    fieldKeys[i++] = fieldKey;
                }
                pipeline.hdel(profile + entry.getKey(), fieldKeys);
            }
            //执行
            pipeline.sync();
        } catch (Exception e) {
            throw e;
        } finally {
            if (pipeline != null) {
                try {
                    pipeline.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (jedis != null) {
                jedis.close();
            }
        }

        return 0;
    }

    @Override
    public List<Object> batchMultipleSet(String prefix, List<Map<String, String>> multiValues) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            for (Map<String, String> multiValue : multiValues) {
                Set<Map.Entry<String, String>> entrySet = multiValue.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    pipeline.set(profile + prefix + ":" + entry.getKey(), entry.getValue());
                }
            }
            return pipeline.syncAndReturnAll();
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public String getTime() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.time().get(0);
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String watch(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String watch = jedis.watch(key);
            return watch;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Object executeWithLock(String lockKey, boolean flag, CallBack callBack) {
        if (flag) {
            lockKey = "lock" + profile + lockKey;
        } else {
            lockKey = "lock" + lockKey;
        }
        if (acquire(lockKey)) {
            try {
                return callBack.onGetLock();
            } finally {
                releaseLock(lockKey);
            }
        } else {
            return callBack.onTimeOut();
        }
    }

    /**
     * 获取锁
     *
     * @param lockKey
     * @return
     */
    private boolean acquire(String lockKey) {
        Jedis jedis = null;
        boolean locked = false;
        try {
            jedis = jedisPool.getResource();
            int timeout = 3000;
            while (timeout >= 0) {
                long expires = System.currentTimeMillis() + 7000 + 1;
                String expiresStr = String.valueOf(expires);
                if (jedis.setnx(lockKey, expiresStr) == 1) {
                    locked = true;
                    return locked;
                }
                String currentValueStr = jedis.get(lockKey); //获取锁的标识
                //判断锁标识否为空
                //如果为空，则循环重新尝试获取锁
                //不为空的时候，如果被其他线程设置了值，则第二个if条件判断是过不去的
                if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {   // lock is expired （锁已经超时）
                    //获取上一个锁到期时间，并设置现在的锁的超时时间，
                    //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                    String oldValueStr = jedis.getSet(lockKey, expiresStr);
                    //如过多个线程恰好都到了这里，但是只有当前线程的设置值和当前值相同，他才有权利获取锁
                    if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                        locked = true;
                        return locked;
                    }
                }
                timeout -= 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return false;

        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 释放锁
     *
     * @param lockkey
     */
    public long releaseLock(String lockkey) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            long current = System.currentTimeMillis();
            // 避免删除非自己获取得到的锁
            String retIdentifier = jedis.get(lockkey);
            if (retIdentifier != null && current < Long.valueOf(retIdentifier)) {
                return jedis.del(lockkey);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }
}
