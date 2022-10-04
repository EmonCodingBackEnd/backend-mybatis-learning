package com.coding.mybatis.demo3.mapper;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.coding.mybatis.demo3.pojo.Emp;
import com.coding.mybatis.demo3.utils.SqlSessionUtils;

public class DynamicSqlMapperTest {

    /*
     * 动态SQL： 
     * 1、if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中。
     *
     * 2、where：
     * 当 where 标签中有内容时，会自动生成 where 关键字，并且将内容前多余的 and 或 or 去掉
     * 当 where 标签中没有内容时，此时 where 标签没有任何效果。
     * 注意：where标签不能将其中的内容后面的 and 或 or 去掉
     *
     * 3、trim：
     * 若标签中有内容时：
     * prefix|suffix：将trim标签中内容前面或后面添加指定内容
     * prefixOverrides|suffixOverrides：将trim标签中内容前面或后面去掉指定内容
     * 若标签中没有内容时：此时trim标签没有任何效果的
     *
     * 4、choose、when、otherwise，相当于if...else if...else
     * when至少要有一个，otherwise最多只能有一个
     *
     * 5、foreach：
     * collection：设置需要循环的数组或集合
     * item：表示数组或集合中的每一个数据
     * separator：循环体之间的分隔符
     * open：foreach标签所循环的所有内容的开始符
     * close：foreach标签所循环的所有内容的结束符
     *
     * 6、sql标签：
     * 设置SQL片段：<sql id="empColumns">eid, emp_name, age, sex, email</sql>
     * 设置SQL片段：<include refid="empColumns"/>
     */

    @Test
    public void testGetEmpByConditionOne() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        emp.setEmpName("张三");
        emp.setAge(23);
        emp.setSex("男");
        emp.setEmail("123456@qq.com");
        List<Emp> empByCondition = mapper.getEmpByConditionOne(emp);
        System.out.println(empByCondition);
    }

    @Test
    public void testGetEmpByConditionTwo() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        emp.setEmpName("张三");
        emp.setAge(23);
        emp.setSex("男");
        emp.setEmail("123456@qq.com");
        List<Emp> empByCondition = mapper.getEmpByConditionTwo(emp);
        System.out.println(empByCondition);
    }

    @Test
    public void testGetEmpByConditionThree() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        emp.setEmpName("张三");
        emp.setAge(23);
        emp.setSex("男");
        emp.setEmail("123456@qq.com");
        List<Emp> empByCondition = mapper.getEmpByConditionThree(emp);
        System.out.println(empByCondition);
    }

    @Test
    public void testGetEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        // emp.setEmpName("张三");
        // emp.setAge(23);
        emp.setSex("男");
        emp.setEmail("123456@qq.com");
        List<Emp> empByCondition = mapper.getEmpByChoose(emp);
        System.out.println(empByCondition);
    }

    @Test
    public void testDeleteMoreByArrayOne() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        int result = mapper.deleteMoreByArrayOne(new Integer[] {6, 7, 8});
        System.out.println(result);
    }

    @Test
    public void testDeleteMoreByArrayTwo() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        int result = mapper.deleteMoreByArrayTwo(new Integer[] {6, 7, 8});
        System.out.println(result);
    }

    @Test
    public void testInsertMoreByList() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp1 = new Emp(null, "a1", 23, "男", "123@qq.com");
        Emp emp2 = new Emp(null, "a2", 23, "男", "123@qq.com");
        Emp emp3 = new Emp(null, "a3", 23, "男", "123@qq.com");
        int result = mapper.insertMoreByList(Arrays.asList(emp1, emp2, emp3));
        System.out.println(result);
    }
}