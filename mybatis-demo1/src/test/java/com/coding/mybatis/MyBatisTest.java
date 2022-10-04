package com.coding.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.coding.mybatis.demo1.pojo.User;
import com.coding.mybatis.demo1.mapper.UserMapper;

public class MyBatisTest {

    @Test
    public void testMyBatis() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取SqlSession：默认就是false
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            // 获取mapper接口对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            // 测试功能
            int result = mapper.insertUser();
            System.out.println(result);

            // 提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void testMyBatis2() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            // 获取mapper接口对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            // 测试功能
            int result = mapper.insertUser();
            System.out.println(result);
        }
    }

    @Test
    public void testUpdate() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            // 获取mapper接口对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            // 测试功能
            mapper.updateUser();
        }
    }

    @Test
    public void testDelete() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            // 获取mapper接口对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            // 测试功能
            mapper.deleteUser();
        }
    }

    @Test
    public void testGetUserById() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            // 获取mapper接口对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            // 测试功能
            User user = mapper.getUserById();
            System.out.println(user);
        }
    }

    @Test
    public void testGetAllUser() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 获取SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            // 获取mapper接口对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            // 测试功能
            List<User> users = mapper.getAllUser();
            users.forEach(System.out::println);
        }
    }
}
