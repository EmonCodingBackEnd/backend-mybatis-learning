package com.coding.mybatis.demo3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coding.mybatis.demo3.pojo.Emp;

public interface EmpMapper {

    /**
     * 查询所有员工信息
     */
    List<Emp> getAllEmpOld();

    /**
     * 查询所有员工信息
     */
    List<Emp> getAllEmp();

    /**
     * 查询员工以及员工所对应的部门信息
     */
    Emp getEmpAndDept(@Param("eid") Integer eid);

    /**
     * 通过分步查询，查询员工以及员工所对应的部门信息
     *
     * 分步查询第一步：查询员工信息
     */
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);

    /**
     * 通过分步查询，查询部门以及部门中所有的员工信息
     *
     * 分布查询第二步：通过did查询部门包含的员工
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("did") Integer did);

}
