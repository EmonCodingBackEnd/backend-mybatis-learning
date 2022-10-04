package com.coding.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.coding.mybatis.mapper.EmpMapper;
import com.coding.mybatis.pojo.Emp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class PageHelperTest {

    /*
     * limit index,pageSize
     * index：当前页的起始索引
     * pageSize：每页显示的条数
     * pageNum：当前页的页码
     * 
     * index = (pageNum -1)*pageSize
     *
     * 使用MyBatis的分页插件实现分页功能：
     * 1、需要在查询功能之前开启分页
     * PageHelper.startPage(int pageNum, int pageSize);
     * 2、在查询功能之后获取分页相关信息
     * PageInfo<Emp> page = new PageInfo<>(list, 5);
     * list表示分页数据
     * 5表示当前导航分页的数量
     */
    @Test
    public void testPageHelper() {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

            /*PageHelper.startPage(2, 5);
            List<Emp> emps = mapper.selectByExample(null);
            emps.forEach(System.out::println);*/

            /*Page<Emp> page = PageHelper.startPage(2, 5);
            List<Emp> emps = mapper.selectByExample(null);
            System.out.println(page);
            emps.forEach(System.out::println);*/

            PageHelper.startPage(2, 5);
            List<Emp> emps = mapper.selectByExample(null);
            PageInfo<Emp> page = new PageInfo<>(emps, 5);
            System.out.println(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
