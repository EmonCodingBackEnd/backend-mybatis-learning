package com.coding.mybatis.demo3.mapper;

import com.coding.mybatis.demo3.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {

    Emp getEmpByEid(@Param("eid") Integer eid);

    void insertEmp(Emp emp);

    int updateEmp(Emp emp);
}
