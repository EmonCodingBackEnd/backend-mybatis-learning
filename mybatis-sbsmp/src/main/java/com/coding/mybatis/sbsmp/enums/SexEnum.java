package com.coding.mybatis.sbsmp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum {
    MALE(1, "男"), FEMALE(2, "女"),;

    // MyBatisPlus：将注解标识的属性的值保存到数据库中
    @EnumValue
    private final Integer sex;

    private final String sexName;

}
