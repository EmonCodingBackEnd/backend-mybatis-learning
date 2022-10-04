package com.coding.mybatis.sbsm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.coding.mybatis.sbsm.domain.mapper")
@SpringBootApplication
public class MybatisSbsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisSbsmApplication.class, args);
    }

}
