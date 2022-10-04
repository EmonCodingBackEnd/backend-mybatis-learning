package com.coding.mybatis.demo3.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.coding.mybatis.demo3.pojo.Emp;
import com.coding.mybatis.demo3.utils.SqlSessionUtils;

public class CacheMapperTest {
    /*
     * MyBatis的一级缓存：
     * 一级缓存是SqlSession级别的，通过同一个SqlSession查询的数据会被缓存，下次查询相同的数据，就会从缓存中直接获取，不会从数据库重新访问。
     * 一级缓存失效的四种情况：
     * 1)不同的SqlSession对应不同的一级缓存
     * 2)同一个SqlSession但是查询条件不同
     * 3)同一个SqlSession两次查询期间执行了任何一次增删改操作
     * 4)同一个SqlSession两次查询期间手动清空了缓存
     *
     * MyBatis的二级缓存：
     * 二级缓存是SqlSessionFactory级别（其实是Mapper级别的，也即是namespace级别的），
     * 通过同一个SqlSessionFactory创建的SqlSession查询的结果会被缓存；此后若再次执行相同的查询语句，结果会从缓存中获取。
     * 二级缓存开启的条件：
     * a>在核心配置文件中，设置全局配置属性 cacheEnabled="true"，默认为true，不需要设置
     * b>在映射文件中设置标签<cache />
     * c>二级缓存必须在SqlSession关闭或提交之后有效
     * d>查询的数据所转换的实体类类型必须实现序列化的接口
     * 使二级缓存失效的情况：
     * 两次查询之间执行了任意的增删改，会使一级和二级缓存同时失效。
     * 
     * 二级缓存的相关配置：
     * eviction属性：缓存回收策略
     * LRU（Least Recently Used） - 最近最少使用的：移除最长时间不被使用的对象。【默认】
     * FIFO（First in First out） - 先进先出：按对象进入缓存的顺序来移除它们。
     * SOFT - 软引用：移除基于垃圾收集器状态和软引用规则的对象。
     * WEAK - 弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。
     *
     * flushInterval属性：刷新间隔，单位毫秒
     * 默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句时刷新
     *
     * size属性：引用数目，正整数
     * 代表缓存最多可以存储多少个对象，太大容易导致内存溢出。
     *
     * readOnly属性：只读，true/false，默认false
     * true：只读缓存；会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了很重要的性能优势。
     * false：读写缓存；会返回缓存对象的拷贝（通过序列化）。这会慢一些，但是安全，因此默认是false。
     * 
     * MyBatis缓存查询的顺序：
     * 先查询二级缓存，因为二级缓存中可能好UI有其他程序以及查出来的数据，可以拿来直接使用。
     * 如果二级缓存没有命中，再查询一级缓存
     * 如果一级缓存也没有命中，则查询数据库
     * SqlSession关闭后，以及缓存中的数据会写入二级缓存。
     */

    @Test
    public void testLevelOneCacheValid() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp = mapper.getEmpByEid(1);
        System.out.println(emp);

        // 从一级缓存中查询（同一个sqlSession）
        emp = mapper.getEmpByEid(1);
        System.out.println(emp);

        // 哪怕更换了Mappper，也依旧生效
        mapper = sqlSession.getMapper(CacheMapper.class);
        emp = mapper.getEmpByEid(1);
        System.out.println(emp);
    }

    @Test
    public void testLevelOneCacheInValidDueChgSqlSession() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp = mapper.getEmpByEid(1);
        System.out.println(emp);

        // 更换了sqlSession，不再有缓存
        sqlSession = SqlSessionUtils.getSqlSession();
        mapper = sqlSession.getMapper(CacheMapper.class);
        emp = mapper.getEmpByEid(1);
        System.out.println(emp);
    }

    @Test
    public void testLevelOneCacheInValidDueChgCondition() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp = mapper.getEmpByEid(1);
        System.out.println(emp);

        // 更换了查询条件，不再有缓存
        emp = mapper.getEmpByEid(2);
        System.out.println(emp);

        // 如果条件变更回来，会继续走缓存
        emp = mapper.getEmpByEid(1);
        System.out.println(emp);
    }

    @Test
    public void testLevelOneCacheInValidDueInsert() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp = mapper.getEmpByEid(1);
        System.out.println(emp);

        // 执行插入，破坏一级缓存
        emp = new Emp(null, "a1", 23, "男", "123@qq.com");
        mapper.insertEmp(emp);

        // 从一级缓存中查询（同一个sqlSession）
        emp = mapper.getEmpByEid(1);
        System.out.println(emp);
    }

    @Test
    public void testCacheInValidDueClearCache() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp = mapper.getEmpByEid(1);
        System.out.println(emp);

        // 执行插入，破坏一级缓存
        sqlSession.clearCache();

        // 从一级缓存中查询（同一个sqlSession）
        emp = mapper.getEmpByEid(1);
        System.out.println(emp);
    }

    @Test
    public void testLevelTwoCache() {
        SqlSessionFactory sqlSessionFactory = SqlSessionUtils.getSqlSessionFactory();

        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);
        sqlSession1.commit();

        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }

    @Test
    public void testLevelTwoCacheInvalid() {
        SqlSessionFactory sqlSessionFactory = SqlSessionUtils.getSqlSessionFactory();

        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        // 执行插入，破坏二级级缓存
        Emp emp = new Emp(null, "a1", 23, "男", "123@qq.com");
        mapper1.insertEmp(emp);

        // 注意：提交必须在破坏之前，否则无法破坏二级缓存。
        sqlSession1.commit();

        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }

}