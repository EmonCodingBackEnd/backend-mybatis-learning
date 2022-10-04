package com.coding.mybatis.demo3.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.coding.mybatis.demo3.pojo.Emp;
import com.coding.mybatis.demo3.utils.SqlSessionUtils;

public class EmpMapperTest {

    /*
    解决字段名和属性名不一致的情况：
    a>为字段起别名，保持和属性名的一致。
    b>设置全局配置，将_自动映射为驼峰
    <setting name="mapUnderscoreToCamelCase" value="true"/>
    c>通过resultMap设置自定义的映射关系
    <resultMap id="empResultMap" type="Emp">
        <id property="eid" column="eid"/>
        <result property="empName" column="emp_name"/>
        <result property="age" column="age"/>
        <result property="sex" column="sex"/>
        <result property="email" column="email"/>
    </resultMap>
    
    处理多对一的映射关系：
    a>级联属性赋值
    b>association
    c>分步查询（还能结合延迟查询，更优）
     */
    @Test
    public void testGetAllEmpOld() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> allEmp = mapper.getAllEmpOld();
        System.out.println(allEmp);
    }

    @Test
    public void testGetAllEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> allEmp = mapper.getAllEmp();
        System.out.println(allEmp);
    }

    @Test
    public void testGetEmpAndDept() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDept = mapper.getEmpAndDept(1);
        System.out.println(empAndDept);
    }

    @Test
    public void testGetEmpAndDeptByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDept = mapper.getEmpAndDeptByStepOne(1);
        // System.out.println(empAndDept); // 哪怕配置了延迟加载，也会导致全部查询
        System.out.println(empAndDept.getEmpName()); // 配置了延迟加载，只会查询员工信息

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(empAndDept.getDept()); // 配置了延迟加载，会再查询对应的部门信息
    }

}