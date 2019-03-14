package com.lee.star.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.code.Style;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

/**
 * MyBatis扫描接口
 * Created by Kaijia Wei on 2016/9/6.
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)//由于MapperScannerConfiguer执行的较早，所以必须有本注解，否则会报错
public class MyBatisMapperScannerConfig {

    private static final String SQL_SESSION_FACTORY = "sqlSessionFactory";

    /**
     * 不能通过配置文件的方式，该类优先于配置文件扫描,逗号分隔
     */
    private static final String DAO_PACKAGE =
            "com.lee.star.dao," +
                    "com.lee.star.demo.dao";

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(SQL_SESSION_FACTORY);
        mapperScannerConfigurer.setBasePackage(DAO_PACKAGE);
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperHelper mapperScan(SqlSessionFactory sqlSessionFactory) {
        MapperHelper mapperHelper = new MapperHelper();
//        特殊配置
        Config config = new Config();
//        设置生成UUID的方法，需要用OGNL方式配置，不限制返回值，但是必须和字段类型匹配
//         config.setUUID(xxx);
//         取回主键的方式
//         config.setIDENTITY("SELECT nextval('t_user_id_seq') ");
//         <selectKey>中的order属性，可选值为BEFORE和AFTER postgre先要获取主键序列
        config.setOrder("BEFORE");
        //数据库的catalog，如果设置该值，查询的时候表名会带catalog设置的前缀
        //config.setCatalog(xxx);
        //同catalog，catalog优先级高于schema
        //config.setSchema(xxxx);
        //seqFormat：序列的获取规则,使用{num}格式化参数，默认值为{0}.nextval，针对Oracle，可选参数一共4个，对应0,1,2,3分别为SequenceName，ColumnName, PropertyName，TableName
        // notEmpty：insert和update中，是否判断字符串类型!=''，少数方法会用到
        config.setNotEmpty(false);
        // style：实体和表转换时的规则，默认驼峰转下划线，可选值为normal用实体名和字段名;camelhump是默认值，驼峰转下划线;uppercase转换为大写;lowercase转换为小写
        config.setStyle(Style.camelhump);
        // enableMethodAnnotation：可以控制是否支持方法上的JPA注解，默认false。
        config.setEnableMethodAnnotation(true);
        //设置配置
        mapperHelper.setConfig(config);

        // 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
        mapperHelper.registerMapper(Mapper.class);
        //配置完成后，执行下面的操作
        mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
        return mapperHelper;
    }
}
