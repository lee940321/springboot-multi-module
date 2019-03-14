package com.lee.star.demo.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:
 * @author: lidanfeng
 * @create: 2019/2/28 19:47
 * @company: www.ideabinder.com
 */
@Data
@Table(name="demo")
public class Demo {
    @Id
    private Integer id;

    private String name;
}
