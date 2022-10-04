package com.coding.mybatis.sbsmp.multids;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding.mybatis.sbsmp.multids.pojo.Product;
import com.coding.mybatis.sbsmp.multids.pojo.User;
import com.coding.mybatis.sbsmp.multids.service.ProductService;
import com.coding.mybatis.sbsmp.multids.service.UserService;

@SpringBootTest
class MybatisSbsmpMultidsApplicationTests {

    @Test
    void contextLoads() {}

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Test
    public void test() {
        User user = userService.getById(1L);
        Product product = productService.getById(1L);
    }

}
