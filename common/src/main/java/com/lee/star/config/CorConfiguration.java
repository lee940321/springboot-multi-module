package com.lee.star.config;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 解决测试环境联调时的跨域问题
 * 生产及外部测试环境已经不需要
 **/

@Configuration
@EnableAutoConfiguration
public class CorConfiguration {

    private Logger logger = LoggerFactory.getLogger(CorConfiguration.class);
    @Value("${star.allowcor}")
    private boolean allowCors = false;

    @Value("${star.allow.cache}")
    private boolean allowCache = false;

    @Bean
    FilterRegistrationBean corsFilter(@Value("${tagert.origin:*}") String origin) {

        return new FilterRegistrationBean(new Filter() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
                    throws IOException, ServletException {
                if (allowCors) {
                    HttpServletResponse response = (HttpServletResponse) res;
                    HttpServletRequest request = (HttpServletRequest) req;
                    String method = request.getMethod();
                    if ("*".equals(origin)) {
                        response.setHeader("Access-Control-Allow-Origin", origin);
                    } else {
                        String[] originSet = origin.split(",");
                        List<String> list = Arrays.asList(originSet);
                        if (CollectionUtils.isNotEmpty(list)) {
                            String originHeader = request.getHeader("Origin");
                            logger.debug("======originHeader:{}======", originHeader);
                            if (list.contains(originHeader)) {
                                response.setHeader("Access-Control-Allow-Origin", originHeader);
                            }
                        }
                    }
                    response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
                    response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
                    //   response.setHeader("Access-Control-Allow-Credentials", "true");
                    response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization" +
                            ",token");

                    if (!allowCache) {
                        response.setHeader("Pragma", "no-cache");
                        response.addHeader("Cache-Control", "must-revalidate");
                        response.addHeader("Cache-Control", "no-cache");
                        response.addHeader("Cache-Control", "no-store");
                        response.setDateHeader("Expires", 0);
                    }
                    if ("OPTIONS".equals(method)) {
                        response.setStatus(HttpStatus.OK.value());
                    } else {
                        chain.doFilter(req, res);
                    }
                } else {
                    chain.doFilter(req, res);
                }

            }

            @Override
            public void init(FilterConfig filterConfig) {
            }

            @Override
            public void destroy() {
            }
        });
    }

    public boolean isAllowCors() {
        return allowCors;
    }

    public void setAllowCors(boolean allowCors) {
        this.allowCors = allowCors;
    }
}
