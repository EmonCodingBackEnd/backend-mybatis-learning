package com.coding.mybatis.sbsmp;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding.mybatis.sbsmp.enums.SexEnum;
import com.coding.mybatis.sbsmp.mapper.UserMapper;
import com.coding.mybatis.sbsmp.pojo.User;

@SpringBootTest
public class MyBatisPlusEnumTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testEnum() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result:" + result);
    }
}
