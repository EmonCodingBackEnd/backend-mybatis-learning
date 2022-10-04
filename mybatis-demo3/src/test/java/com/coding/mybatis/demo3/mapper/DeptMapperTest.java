package com.coding.mybatis.demo3.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.coding.mybatis.demo3.pojo.Dept;
import com.coding.mybatis.demo3.utils.SqlSessionUtils;

public class DeptMapperTest {

    /*
    处理一对多的映射关系：
    a>collection
    b>分步查询
     */

    @Test
    public void testGetDeptAndEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmp(1);
        System.out.println(dept);
    }

    @Test
    public void testGetDeptAndEmpByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpByStepOne(1);
        // System.out.println(dept); // 哪怕配置了延迟加载，也会导致全部查询
        System.out.println(dept.getDeptName()); // 配置了延迟加载，只会查询部门信息

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(dept.getEmps()); // 配置了延迟加载，会再查询对应的员工信息
    }

}