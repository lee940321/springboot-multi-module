package com.lee.star.job.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:jobs.xml"})
public class ElJobConfiguration {

}
