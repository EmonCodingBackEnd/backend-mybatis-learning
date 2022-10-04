package com.coding.mybatis.ssm.mapper;

import java.util.List;

import com.coding.mybatis.ssm.pojo.Employee;

public interface EmployeeMapper {

    /**
     * 查询所有的员工信息
     */
    List<Employee> getAllEmployee();
}
