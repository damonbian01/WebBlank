package com.spring.meituan.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by biantao on 16/6/29.
 */
public class DBTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-datasource.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

        String sql = "select id from tb_user";
        List<Integer> list = jdbcTemplate.query(sql, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });

        System.out.println(list.size());
    }

}
