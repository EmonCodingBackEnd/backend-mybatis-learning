package com.coding.mybatis.sbsmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.mybatis.sbsmp.mapper.UserMapper;
import com.coding.mybatis.sbsmp.pojo.User;
import com.coding.mybatis.sbsmp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
