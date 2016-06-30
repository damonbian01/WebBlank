package com.spring.meituan.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by biantao on 16/6/30.
 */
public class TestUservo {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestUservo(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
