package com.lee.star.common.component.aop;

import com.lee.star.common.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 接口调用日志记录AOP实现
 */
@Component
@Aspect
@Order(1)
public class LogAspect {

    final static String REAL_IP_NAME = "X-Forward-For";
    private ThreadLocal<String> hosCode = new ThreadLocal<>();
    private ThreadLocal<String> ip = new ThreadLocal<>();//ip,通常是ng
    private ThreadLocal<String> realIp = new ThreadLocal<>();//真实客户端ip

    private Logger log = LoggerFactory.getLogger("controllerLog");

    private ThreadLocal<String> requestPath = new ThreadLocal<>(); // 请求地址
    private ThreadLocal<Map<String, String[]>> inputParamMap = new ThreadLocal<>(); // 传入参数
    private ThreadLocal<Long> startTimeMillis = new ThreadLocal<>(); // 开始时间
    private ThreadLocal<Long> endTimeMillis = new ThreadLocal<>(); // 结束时间
    private ThreadLocal<Object> result = new ThreadLocal<>();


    @Before("execution(!private * com.lee.star.web.controller..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        startTimeMillis.set(System.currentTimeMillis());
    }

    @After("execution(!private * com.lee.star.web.controller..*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        endTimeMillis.set(System.currentTimeMillis());
        //打印日志
        this.printOptLog();
        clear();
    }

    @Around("execution(!private * com.lee.star.web.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取输入参数
        inputParamMap.set(request.getParameterMap());
        // 获取请求地址
        requestPath.set(request.getRequestURI());
        //请求ip
        ip.set(request.getRemoteAddr() == null ? "" : request.getRemoteAddr());
        //请求real ip
        realIp.set(request.getHeader(REAL_IP_NAME) == null ? "" : request.getHeader(REAL_IP_NAME));

        result.set(pjp.proceed());// result的值就是被拦截方法的返回值
        return result.get();
    }

    private void clear() {
        this.startTimeMillis.set(null);
        this.endTimeMillis.set(null);
        this.requestPath.set(null);
        this.inputParamMap.set(null);
        this.result.set(null);
        this.ip.set(null);
        this.realIp.set(null);
        this.hosCode.set(null);
    }

    /**
     * 打印日志
     */
    private void printOptLog() {
        String optTime = new DateTime(startTimeMillis.get()).toString("yyyy-MM-dd HH:mm:ss");
        log.info("\n############################ start ######################################\n" +
                        "## ip ={},real ip ={}\n" +
                        "## url ={}\n" +
                        "## op_time ={}\n" +
                        "## pro_time ={}ms\n" +
                        "## request ={}\n" +
                        "## response ={}\n" +
                        "################################# end #################################\n", ip.get(), realIp.get(), requestPath.get(), optTime,
                endTimeMillis.get()
                        - startTimeMillis.get(), JsonUtils.objectToJson(inputParamMap.get()), JsonUtils.objectToJson(result.get()));
    }


}
