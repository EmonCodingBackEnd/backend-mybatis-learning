package com.coding.mybatis.sbsmp;

import java.util.Collections;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class FastAutoGeneratorTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://repo.emon.vip:3306/mybatisdb?useSSL=false";
        String username = "root";
        String password = "root123";
        FastAutoGenerator.create(url, username, password).globalConfig(builder -> {
            builder.author("emon") // 设置作者
                // .enableSwagger() // 开启 swagger 模式
                .fileOverride() // 覆盖已生成文件
                .outputDir("D://mybatis-plus"); // 指定输出目录
        }).packageConfig(builder -> {
            builder.parent("com.coding.mybatis") // 设置父包名
                .moduleName("sbsmp") // 设置父包模块名
                .pathInfo(Collections.singletonMap(OutputFile.xml, "D://mybatis-plus")); // 设置mapperXml生成路径
        }).strategyConfig(builder -> {
            builder.addInclude("mp_user") // 设置需要生成的表名
                .addTablePrefix("mp_"); // 设置过滤表前缀
        }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();

    }
}
