package com.coding.mybatis.sbsm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    private Integer eid;

    private String empName;

    private Integer age;

    private String sex;

    private String email;

}
