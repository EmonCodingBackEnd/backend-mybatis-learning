package com.coding.mybatis.demo2.mapper;

import java.sql.*;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import com.coding.mybatis.demo2.pojo.User;
import com.coding.mybatis.demo2.utils.SqlSessionUtils;

public class SqlMapperTest {

    @Test
    public void testGetUserByLike() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpecialSqlMapper mapper = sqlSession.getMapper(SpecialSqlMapper.class);
        List<User> users = mapper.getUserByLike("a");
        System.out.println(users);
    }

    @Test
    public void testDeleteMore() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpecialSqlMapper mapper = sqlSession.getMapper(SpecialSqlMapper.class);
        int result = mapper.deleteMore("1,2,3");
        System.out.println(result);
    }

    @Test
    public void testUserByTableName() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpecialSqlMapper mapper = sqlSession.getMapper(SpecialSqlMapper.class);
        List<User> users = mapper.getUserByTableName("t_user");
        System.out.println(users);
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpecialSqlMapper mapper = sqlSession.getMapper(SpecialSqlMapper.class);
        User user = new User();
        user.setUsername("王五");
        user.setPassword("123456");
        user.setAge(23);
        user.setSex("男");
        user.setEmail("123456@qq.com");
        mapper.insertUser(user);
        System.out.println(user);
    }

    @Ignore
    @Test
    public void testJDBC() throws Exception {
        String username = "";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("", "", "");
        PreparedStatement preparedStatement =
            connection.prepareStatement("insert into t_user values (null,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

    }
}