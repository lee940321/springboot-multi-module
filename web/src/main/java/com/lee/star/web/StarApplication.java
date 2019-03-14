package com.lee.star.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@ComponentScan({"com.lee.star"})
public class StarApplication {


    public static void main(String[] args) {
        SpringApplication.run(StarApplication.class, args);
        log.info("############################################");
        log.info("########        STAR启动完毕       ########");
        log.info("############################################");
    }

}
