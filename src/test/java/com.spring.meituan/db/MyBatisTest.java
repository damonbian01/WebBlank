package com.spring.meituan.db;

import com.spring.meituan.dao.TestUserMapper;
import com.spring.meituan.vo.TestUservo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by biantao on 16/7/12.
 */
public class MyBatisTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-datasource.xml");
        SqlSession session = ((SqlSessionFactory) applicationContext.getBean("sqlSessionFactory")).openSession();
        TestUserMapper testUserMapper = session.getMapper(TestUserMapper.class);
        List<TestUservo> vos = testUserMapper.listTestUser();
        session.close();
        for (TestUservo vo : vos) {
            System.out.println(vo.toString());
        }
    }
}
