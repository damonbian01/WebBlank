package com.spring.meituan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by biantao on 16/6/29.
 */
@Controller
public class TestController {

    @RequestMapping( value = "/test")
    public String test() {
        return "test";
    }
}
