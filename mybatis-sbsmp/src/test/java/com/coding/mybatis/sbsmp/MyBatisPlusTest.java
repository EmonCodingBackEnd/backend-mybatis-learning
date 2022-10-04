package com.coding.mybatis.sbsmp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.coding.mybatis.sbsmp.mapper.UserMapper;
import com.coding.mybatis.sbsmp.pojo.User;

@SpringBootTest
public class MyBatisPlusTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        // 插入
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("张大帅");
        user.setAge(23);
        user.setEmail("123@qq.com");
        int result = userMapper.insert(user);
        System.out.println("result:" + result);
        System.out.println("id:" + user.getId());
    }

    @Test
    public void testDeleteById() {
        // 通过id删除用户信息
        // DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(1576486529864372225L);
        System.out.println("result:" + result);
    }

    @Test
    public void testDeleteByMap() {
        // 通过map删除用户信息
        // DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张大帅");
        map.put("age", 23);
        int result = userMapper.deleteByMap(map);
        System.out.println("result:" + result);
    }

    @Test
    public void testDeleteBatchIds() {
        // 通过ids删除用户信息
        // DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("result:" + result);
    }

    @Test
    public void testDeleteByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result:" + result);
    }

    @Test
    public void testUpdateById() {
        // 修改用户信息
        // UPDATE user SET name=?, email=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@qq.com");
        int result = userMapper.updateById(user);
        System.out.println("result:" + result);
    }

    @Test
    public void testUpdateByWrapper1() {
        /*
        sql中AND优先级大于OR，所以以下2个相等
        select * from user WHERE deleted=0 AND (name LIKE '%a%' AND age > 20 OR email IS NULL);
        select * from user WHERE deleted=0 AND (name LIKE '%a%' AND age > 20) OR (email IS NULL);
        
        UPDATE user SET name=?, email=? WHERE deleted=0 AND (name LIKE ? AND age > ? OR email IS NULL)
         */
        // 将（用户名包含a，年龄大于20），或者邮箱信息为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a").gt("age", 20).or().isNull("email");
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:" + result);
    }

    @Test
    public void testUpdateByWrapper2() {
        /*
        UPDATE user SET name=?, email=? WHERE deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
         */
        // 将用户名包含a并且（年龄大于20或邮箱信息为null）的用户信息
        // lambda中的条件优先执行
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:" + result);
    }

    @Test
    public void testUpdateByUpdateWrapper() {
        // 将用户名包含a并且（年龄大于20或邮箱信息为null）的用户信息
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("name", "小黑").set("email", "abc@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void testUpdateByLambdaUpdateWrapper() {
        // 将用户名包含a并且（年龄大于20或邮箱信息为null）的用户信息
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName, "a").and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName, "小黑").set(User::getEmail, "abc@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void testSelectById() {
        // 通过id查询用户信息
        // SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(1L);
        System.out.println("user:" + user);
    }

    @Test
    public void testSelectByIds() {
        // 通过ids查询用户信息
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        List<User> userList = userMapper.selectBatchIds(idList);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
        // 通过map查询用户信息
        // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 20);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectList1() {
        // 通过调节构造器查询一个list集合，若没有条件，这可以设置null为参数
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectList2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectList3() {
        // 查询id小于等于100的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id <=100");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectListByWrapper1() {
        // 查询用户名包含a，年龄在20-30之间，邮箱信息不为null的用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", "a").between("age", 20, 30).isNotNull("email");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectListByWrapper2() {
        // 查询用户信息，按照年龄降序排列，若年龄相同，这按照id升序排列
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectMaps1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void testSelectCondition1() {
        String name = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        // SELECT name,age,email FROM user WHERE deleted=0 AND (name = ? AND age <= ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void testSelectCondition2() {
        String name = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        // SELECT name,age,email FROM user WHERE deleted=0 AND (name = ? AND age <= ?)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name).ge(ageBegin != null, "age", ageBegin)
            .le(ageEnd != null, "age", ageEnd);
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void testSelectLambdaWrapper() {
        String name = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        // SELECT name,age,email FROM user WHERE deleted=0 AND (name = ? AND age <= ?)
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.like(StringUtils.isNotBlank(name), User::getName, name)
            .ge(ageBegin != null, User::getAge, ageBegin).le(ageEnd != null, User::getAge, ageEnd);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void testSelectMapById() {
        Map<String, Object> userMap = userMapper.selectMapById(1L);
        System.out.println(userMap);}


}
