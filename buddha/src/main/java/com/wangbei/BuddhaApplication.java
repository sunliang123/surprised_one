package com.wangbei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

/**
* @author yuyidi 2017-07-06 10:25:00
* @class com.wangbei.BuddhaApplication
* @description 启用@EnableAutoConfiguration 及配置@ComponentScan 扫描当前类下及子级的包
*/
@SpringBootApplication
public class BuddhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuddhaApplication.class, args);
    }
}

