package com.coding.mybatis.sbsmp.multids.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.mybatis.sbsmp.multids.mapper.UserMapper;
import com.coding.mybatis.sbsmp.multids.pojo.User;
import com.coding.mybatis.sbsmp.multids.service.UserService;

@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
