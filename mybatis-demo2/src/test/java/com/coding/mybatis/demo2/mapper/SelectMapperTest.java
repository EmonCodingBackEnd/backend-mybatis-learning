package com.coding.mybatis.demo2.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.coding.mybatis.demo2.pojo.User;
import com.coding.mybatis.demo2.utils.SqlSessionUtils;

public class SelectMapperTest {

    /*
     MyBatis的各种查询功能：
     1、若查询出的数据只有一条。
     a>可以通过实体类对象接收
     b>可以通过list集合接收
     c>可以通过map集合接收
     结果：{password=123456, sex=男, id=3, age=23, email=123@qq.com, username=admin}
    
     2、若查询出的数据有多条
     a>可以通过实体类类型的list集合接收
     b>可以通过map类型的list集合接收
     c>可以在mapper接口的方法上添加@MapKey注解，此时就可以将每条数据转换的map集合作为值，以某个字段的值作为键，放在同一个map集合中。
     注意：一定不能通过实体类对象接收，此时会抛出异常 org.apache.ibatis.exceptions.TooManyResultsException
    
     MyBatis中设置了默认的类型别名，详见： mybatis-3.5.10.pdf ==> Configuration XML
     比如：
     java.lang.Integer ==> int,integer
     int ==> _int,_integer
     Map ==> map
     String ==> string
     */

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        User user = mapper.getUserById(3);
        System.out.println(user);
    }

    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> users = mapper.getAllUser();
        System.out.println(users);
    }

    @Test
    public void testGetCount() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Integer count = mapper.getCount();
        System.out.println(count);
    }

    @Test
    public void testGetUserByIdToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> userMap = mapper.getUserByIdToMap(3);
        System.out.println(userMap);
    }

    @Test
    public void testGetAllUserToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<Map<String, Object>> userMapList = mapper.getAllUserToMap();
        System.out.println(userMapList);
    }

    @Test
    public void testGetAllUserToMap2() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> userMap = mapper.getAllUserToMap2();
        System.out.println(userMap);
    }

}