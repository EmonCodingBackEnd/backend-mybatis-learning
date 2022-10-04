package com.coding.mybatis.sbsm.service;

import java.util.List;

import com.coding.mybatis.sbsm.domain.entity.Employee;
import com.github.pagehelper.PageInfo;

public interface EmployeeService {

    /**
     * 查询所有的员工信息
     */
    List<Employee> getAllEmployee();

    PageInfo<Employee> getEmployeePage(Integer pageNum);
}
