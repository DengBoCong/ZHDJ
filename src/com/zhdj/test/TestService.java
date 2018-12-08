package com.zhdj.test;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-07-28 15:41
 **/
public class TestService {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void hello()
    {
        System.out.println("hello "+ getName());
    }
}
