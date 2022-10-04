package com.coding.mybatis.sbsmp.multids;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.coding.mybatis.sbsmp.multids.mapper")
@SpringBootApplication
public class MybatisSbsmpMultidsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisSbsmpMultidsApplication.class, args);
    }

}
