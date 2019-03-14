package com.lee.star.common.component.jedis;

/**
 * redis 锁的回调
 */
public interface CallBack {

    Object onGetLock();

    Object onTimeOut();
}
