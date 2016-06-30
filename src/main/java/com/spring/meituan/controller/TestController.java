package com.spring.meituan.controller;

import com.spring.meituan.vo.TestUservo;
import com.spring.meituan.vo.TestUservo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @RequestMapping ( value = "/testModelView", method = RequestMethod.GET)
    public ModelAndView testModelView() {
        ModelAndView mv = new ModelAndView("test");
        String sql = "select id from tb_user";
        List<Integer> ids = jdbcTemplate.query(sql, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });

        mv.addObject("ids", ids);
        return mv;
    }

    @RequestMapping ( value = "/testPrint")
    public void testPrint(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("this is write test");
        writer.close();
    }

    @RequestMapping ( value = "/testJson/{ids}")
    @ResponseBody
    public TestUservo testJson(@PathVariable (value = "ids") int ids, @RequestParam (value = "name", required = false) String name) {
        return new TestUservo(3, "test");
    }

    @RequestMapping ( value = "/testJson2")
    public void testJson2(HttpServletResponse response) throws IOException {
        String json = "{\"data\":\"nihao\"}";
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }

}
