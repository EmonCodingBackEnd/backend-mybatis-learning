package com.coding.mybatis.sbsmp;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coding.mybatis.sbsmp.mapper.ProductMapper;
import com.coding.mybatis.sbsmp.mapper.UserMapper;
import com.coding.mybatis.sbsmp.pojo.Product;
import com.coding.mybatis.sbsmp.pojo.User;

@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        Assertions.assertEquals(userPage, page);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testPageCustom() {
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userMapper.selectPageVo(page, 20);
        Assertions.assertEquals(userPage, page);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testProduct01() {
        // 小李查询商品价格
        Product productLi = productMapper.selectById(1L);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        // 小王查询商品价格
        Product productWang = productMapper.selectById(1L);
        System.out.println("小王查询的商品价格：" + productLi.getPrice());

        // 小李将商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        int resultLi = productMapper.updateById(productLi);
        System.out.println("小李修改的记录数量：" + resultLi);
        // 小王将商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        int resultWang = productMapper.updateById(productWang);
        System.out.println("小王修改的记录数量：" + resultWang);
        if (resultWang == 0) {
            Product productNew = productMapper.selectById(1L);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }

        // 老板查询商品价格
        Product productBoss = productMapper.selectById(1L);
        System.out.println("老板查询的商品价格：" + productBoss.getPrice());
    }
}
