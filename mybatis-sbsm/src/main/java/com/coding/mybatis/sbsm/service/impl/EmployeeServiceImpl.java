package com.coding.mybatis.sbsm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coding.mybatis.sbsm.domain.entity.Employee;
import com.coding.mybatis.sbsm.domain.mapper.EmployeeMapper;
import com.coding.mybatis.sbsm.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    @Override
    public PageInfo<Employee> getEmployeePage(Integer pageNum) {
        // 开启式分页功能
        Page<Employee> page = PageHelper.startPage(pageNum, 3);
        // 查询所有员工信息
        List<Employee> list = employeeMapper.getAllEmployee();
        // 获取分页相关数据
        PageInfo<Employee> pageInfo = PageInfo.of(list, 3);
        System.out.println(pageInfo);
        return pageInfo;
    }
}
