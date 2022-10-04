package com.coding.mybatis.sbsm.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.coding.mybatis.sbsm.domain.entity.Employee;
import com.coding.mybatis.sbsm.service.EmployeeService;
import com.github.pagehelper.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * 查询所有的员工信息-->/employee-->get
 * 
 * 根据id查询某个员工信息-->/employee-->get
 * 
 * 跳转到添加页面-->/employee-->get
 * 
 * 添加员工信息-->/employee-->post
 * 
 * 修改员工信息-->/employee-->put
 * 
 * 删除员工信息-->/employee/1-->delete
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        log.info("exec getAllEmployee");
        // 查询所有的员工信息
        return employeeService.getAllEmployee();
    }

    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.GET)
    public PageInfo<Employee> getEmployeePage(Model model, @PathVariable("pageNum") Integer pageNum) {
        log.info("exec getEmployeePage");
        // 获取员工的分页信息
        PageInfo<Employee> pageInfo = employeeService.getEmployeePage(pageNum);
        return pageInfo;
    }
}
