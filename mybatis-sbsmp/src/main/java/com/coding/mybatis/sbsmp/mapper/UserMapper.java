package com.coding.mybatis.sbsmp.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coding.mybatis.sbsmp.pojo.User;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户信息为map集合
     */
    Map<String, Object> selectMapById(Long id);

    /**
     * 通过年龄查询用户信息并分页
     * 
     * @param page MyBatis-Plus所提供的分页对象，必须位于第一个参数的位置
     * @param age
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
