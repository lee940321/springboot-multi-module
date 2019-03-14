package com.lee.star.common.component.jedis;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基于jedis封装的一些操作redis方法
 */
public interface JedisClient {

    /**
     * 设置key 对应的Stringvalue
     *
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * 获取 key 对应的String value
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 删除key 对应的String value
     *
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * 设置key 对应的Stringvalue
     *
     * @param key
     * @param value
     * @param flag  是否加profile前缀
     * @return
     */
    String set(String key, String value, boolean flag);

    /**
     * 设置key，只有不存在才能成功
     *
     * @param key
     * @param value
     * @return
     */
    Long setnx(String key, String value, boolean flag);


    /**
     * 获取 key 对应的String value
     *
     * @param key
     * @param flag 是否加profile前缀
     * @return
     */
    String get(String key, boolean flag);

    /**
     * 删除key 对应的String value
     *
     * @param key
     * @param flag 是否加profile前缀
     * @return
     */
    Long del(String key, boolean flag);

    /**
     * 将 key 所储存的值加上增量 increment 。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     * <p>
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     *
     * @param key
     * @param value
     * @param flag
     * @return
     */
    Long incrby(String key, Long value, boolean flag);


    //long incrBy

    /**
     * 设置hash结构的k-v
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hset(String key, String field, String value);

    /**
     * 获取hash结构的 k-v
     *
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     * 返回哈希表 key 中，所有的域和值。
     *
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);


    /**
     * 删除 hash表中的 key中对应的 fields(多个)
     *
     * @param key
     * @param fields
     * @return
     */
    Long hdel(String key, String... fields);

    /**
     * 让key 对应的 value 自增1
     *
     * @param key
     * @return
     */

    Long incr(String key, boolean flag);


    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     * @return
     */

    Long expire(String key, int seconds);

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     * @param flag
     * @return
     */
    Long expire(String key, int seconds, boolean flag);

    /**
     * 查询过期时间
     *
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 增加hash字段的值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    Double hincrByFloat(String key, String field, double value);

    /**
     * 为hash结构key的field加上增量increment
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hincrBy(String key, String field, long value);

    Long hincrBy(String key, String field, long value, Boolean flag);

    /**
     * 批量为field设置value
     *
     * @param key
     * @param map
     * @return
     */
    String hmset(String key, Map<String, String> map);

    /**
     * 当field不存在时，才会对他进行操作
     *
     * @param key
     * @param
     * @return
     */
    Long hsetnxt(String key, String field, String value);


    Long hsetnxt(String key, String field, String value, Boolean flag);

    /**
     * 获取整个集合
     *
     * @param key
     * @return
     */
    Set smembers(String key);

    /**
     * 向集合中加入元素
     *
     * @param key
     * @param member
     * @return
     */
    Long sadd(String key, String member);

    /**
     * 从集合中删除元素
     *
     * @param key
     * @param member
     * @return
     */
    Long srem(String key, String member);

    /**
     * 从集合中删除多个元素
     *
     * @param key
     * @param strArr
     * @return
     */
    Long sremArray(String key, List<String> strArr);

    /**
     * 获取整个集合
     *
     * @param key
     * @param flag
     * @return
     */
    Set smembers(String key, boolean flag);

    /**
     * 向集合中加入元素
     *
     * @param key
     * @param member
     * @param flag
     * @return
     */
    Long sadd(String key, String member, boolean flag);

    /**
     * 从集合中删除元素
     *
     * @param key
     * @param member
     * @param flag
     * @return
     */
    Long srem(String key, String member, boolean flag);

    /**
     * 从集合中删除多个元素
     *
     * @param key
     * @param strArr
     * @param flag
     * @return
     */
    Long sremArray(String key, List<String> strArr, boolean flag);

    /**
     * 向列表中添加元素
     *
     * @param key
     * @param strings
     * @return
     */
    Long lset(String key, String strings);

    /**
     * 从列表中获取数据
     *
     * @param key
     * @return
     */
    List<String> lget(String key);

    /**
     * 从列表中删除数据
     *
     * @param key
     * @param values
     * @return
     */
    Long lrem(String key, String values);

    String setex(String key, int i, String s);

    String setex(String key, int i, String s, Boolean flag);

    /**
     * 查找所有符合给定模式 pattern 的 key
     *
     * @param pattern
     * @return
     */
    Set<String> keys(String pattern);

    /**
     * 通过pipeline通道批量缓存推广
     *
     * @return
     */
    List<Object> batchHashMultipleSet(Map<String, Map<String, String>> multiValues);

    /**
     * 通过pipeline通道清理推广
     *
     * @param multiValues
     * @return
     */
    long batchFlushHashMultipleSet(Map<String, Map<String, String>> multiValues);


    List<Object> batchMultipleSet(String prefix, List<Map<String, String>> multiValues);

    /**
     * 获取Redis当前时间
     *
     * @return
     */
    String getTime();

    /**
     * Watch 命令用于监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打
     *
     * @param key
     * @return
     */
    String watch(String key);

    /**
     * redis同步锁操作
     *
     * @param lockKey  锁的id
     * @param flag     是否增加profile作为前缀
     * @param callBack 回调函数，使用者自己实现
     */
    Object executeWithLock(String lockKey, boolean flag, CallBack callBack);

}
