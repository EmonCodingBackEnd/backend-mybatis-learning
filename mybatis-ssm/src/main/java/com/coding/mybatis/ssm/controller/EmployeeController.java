package com.coding.mybatis.ssm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coding.mybatis.ssm.pojo.Employee;
import com.coding.mybatis.ssm.service.EmployeeService;
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
@Controller
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public String getAllEmployee(Model model) {
        log.info("exec getAllEmployee");
        // 查询所有的员工信息
        List<Employee> list = employeeService.getAllEmployee();
        // 将员工信息在请求域中共享
        model.addAttribute("list", list);
        // 跳转到employee_list.html
        return "employee_list";
    }

    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.GET)
    public String getEmployeePage(Model model, @PathVariable("pageNum") Integer pageNum) {
        log.info("exec getEmployeePage");
        // 获取员工的分页信息
        PageInfo<Employee> pageInfo = employeeService.getEmployeePage(pageNum);
        // 将员工的信息在请求域中共享
        model.addAttribute("page", pageInfo);
        // 跳转到employee_list_page.html
        return "employee_list_page";
    }
}
