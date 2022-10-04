package com.coding.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.coding.mybatis.mapper.EmpMapper;
import com.coding.mybatis.pojo.Emp;

public class MbgTest {

    @Test
    public void testMbg() {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

            // 查询所有数据
            /*List<Emp> emps = mapper.selectByExample(null);
            emps.forEach(System.out::println);*/

            // 根据条件查询
            /*EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThanOrEqualTo(20);
            example.or().andDidIsNotNull();
            List<Emp> emps = mapper.selectByExample(example);
            emps.forEach(System.out::println);*/

            // mapper.updateByPrimaryKey(new Emp(14, "admin", 22, null, "456@qq.com", 3));
            mapper.updateByPrimaryKeySelective(new Emp(14, "admin", 22, null, "456@qq.com", 3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
