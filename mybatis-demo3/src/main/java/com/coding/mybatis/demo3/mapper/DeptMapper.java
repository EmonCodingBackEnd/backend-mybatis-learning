package com.coding.mybatis.demo3.mapper;

import org.apache.ibatis.annotations.Param;

import com.coding.mybatis.demo3.pojo.Dept;

public interface DeptMapper {

    /**
     * 通过分步查询，查询员工以及员工所对应的部门信息
     *
     * 分步查询第二步：通过did查询员工所对应的部门
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);

    /**
     * 获取部门以及部门中所有的员工信息
     */
    Dept getDeptAndEmp(@Param("did") Integer did);

    /**
     * 通过分步查询，查询部门以及部门中所有的员工信息
     *
     * 分步查询第一步：通过did查询部门信息
     */
    Dept getDeptAndEmpByStepOne(@Param("did") Integer did);
}
