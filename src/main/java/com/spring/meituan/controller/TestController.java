package com.spring.meituan.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by biantao on 16/6/29.
 */
@Controller
public class TestController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping( value = "/test")
    public String test(HttpServletRequest request) {
        String sql = "select id from tb_user";
        List<Integer> ids = jdbcTemplate.query(sql, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });

        request.setAttribute("ids", ids);
        return "test";
    }
}
