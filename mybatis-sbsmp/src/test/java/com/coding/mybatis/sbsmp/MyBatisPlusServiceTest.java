package com.coding.mybatis.sbsmp;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding.mybatis.sbsmp.pojo.User;
import com.coding.mybatis.sbsmp.service.UserService;

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCount() {
        // 查询总记录数
        // SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testSaveBatch() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("emon" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        // INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        boolean bool = userService.saveBatch(list);
        System.out.println(bool);
    }

}
