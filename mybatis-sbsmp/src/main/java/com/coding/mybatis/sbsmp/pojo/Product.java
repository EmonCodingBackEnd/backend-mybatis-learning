package com.coding.mybatis.sbsmp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.Data;

@Data
public class Product {

    // 将属性所对应的字段指定为主键
    @TableId
    private Long id;

    @TableField("name")
    private String name;

    private Integer price;

    // 标识乐观锁版本号字段
    @Version
    private Integer version;
}
