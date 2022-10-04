package com.coding.mybatis.sbsm.domain.mapper;

import java.util.List;

import com.coding.mybatis.sbsm.domain.entity.Employee;


public interface EmployeeMapper {

    /**
     * 查询所有的员工信息
     */
    List<Employee> getAllEmployee();
}
