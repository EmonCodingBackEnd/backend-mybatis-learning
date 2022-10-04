package com.coding.mybatis.demo3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coding.mybatis.demo3.pojo.Emp;

public interface DynamicSqlMapper {

    /**
     * 多条件查询
     */
    List<Emp> getEmpByConditionOne(Emp emp);

    /**
     * 多条件查询
     */
    List<Emp> getEmpByConditionTwo(Emp emp);

    /**
     * 多条件查询
     */
    List<Emp> getEmpByConditionThree(Emp emp);

    /**
     * 测试choose、when、otherwise
     */
    List<Emp> getEmpByChoose(Emp emp);

    /**
     * 通过数组实现批量删除
     */
    int deleteMoreByArrayOne(@Param("eids") Integer[] eids);

    /**
     * 通过数组实现批量删除
     */
    int deleteMoreByArrayTwo(@Param("eids") Integer[] eids);

    /**
     * 通过list集合实现批量添加
     */
    int insertMoreByList(@Param("emps") List<Emp> emps);

}
