package com.coding.mybatis.sbsmp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.coding.mybatis.sbsmp.enums.SexEnum;

import lombok.Data;

@Data
// 设置实体类所对应的表名
@TableName(value = "user", keepGlobalPrefix = true)
public class User {

    // 将属性所对应的字段指定为主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("name")
    private String name;

    private Integer age;

    private SexEnum sex;

    private String email;

    @TableLogic
    private Integer deleted;
}
