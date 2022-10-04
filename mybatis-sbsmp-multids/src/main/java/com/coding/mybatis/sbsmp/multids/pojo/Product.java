package com.coding.mybatis.sbsmp.multids.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.Data;

@Data
@TableName(value = "mp_product")
public class Product {

    // 将属性所对应的字段指定为主键
    @TableId
    private Long id;

    private String name;

    private Integer price;

    // 标识乐观锁版本号字段
    @Version
    private Integer version;
}
