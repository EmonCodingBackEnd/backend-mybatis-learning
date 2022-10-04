package com.coding.mybatis.demo2.mapper;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import com.coding.mybatis.demo2.pojo.User;
import com.coding.mybatis.demo2.utils.SqlSessionUtils;

public class ParameterMapperTest {

    /*
    MyBatis获取参数值的两种方式：${}和#{}
    ${}本质是字符串拼接
    #{}本质是占位符赋值
    
    MyBatis获取参数值的各种情况：
    1、mapper接口方法的参数为单个的字面量类型
    可以通过 ${} 和 #{} 以任意名称获取参数的值，但是要注意 ${} 的单引号问题
    
    2、mapper接口方法的参数为多个时
    此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
    a>以arg0,arg1...为key，以参数为value
    b>以param1,param2...为key，以参数为value
    因此，只需要通过 ${} 和 #{} 以键的方式访问值即可，但是要注意 ${} 的单引号问题
    
    3、若mapper接口方法的参数有多个时，可以手动将这些参数放在一个map中存储
    只需要通过 ${} 和 #{} 以键的方式访问值即可，但是要注意 ${} 的单引号问题
    
    4、mapper接口方法的参数是实体类类型的参数【推荐】
    只需要通过 ${} 和 #{} 以属性的方式访问属性值即可，但是要注意 ${} 的单引号问题
    
    5、使用@Param注解定义命名参数【推荐】
    此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
    a>以@Param注解的值为为key，以参数为value
    b>以param1,param2...为key，以参数为value
    因此，只需要通过 ${} 和 #{} 以键的方式访问值即可，但是要注意 ${} 的单引号问题
     */
    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(System.out::println);
    }

    @Test
    public void testGetUserByUsername() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.getUserByUsername("张三");
        System.out.println(user);
    }

    @Test
    public void testCheckLogin() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLogin("张三", "123456");
        System.out.println(user);
    }

    @Test
    public void testCheckLoginByMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "张三");
        map.put("password", "123456");
        User user = mapper.checkLoginByMap(map);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123456");
        user.setAge(23);
        user.setSex("男");
        user.setEmail("123456@qq.com");
        int result = mapper.insertUser(user);
        System.out.println(result);
    }

    @Test
    public void testCheckLoginByParam() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("李四", "123456");
        System.out.println(user);
    }

    @Ignore
    @Test
    public void testJDBC() throws Exception {
        String username = "";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("", "", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from t_user where username = '" + username + "'");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_user where username = ?");
        preparedStatement.setString(1, username);
        ResultSet resultSet1 = preparedStatement.executeQuery();

    }

}