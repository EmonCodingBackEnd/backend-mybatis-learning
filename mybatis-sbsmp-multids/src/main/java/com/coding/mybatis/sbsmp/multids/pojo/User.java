package com.coding.mybatis.sbsmp.multids.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
// 设置实体类所对应的表名
@TableName(value = "mp_user")
public class User {

    // 将属性所对应的字段指定为主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Integer age;

    private Integer sex;

    private String email;

    @TableLogic
    private Integer deleted;
}
