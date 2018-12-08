package com.zhdj.test;

import com.zhdj.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-07-28 15:42
 **/
public class Test {
    public static void main(String[] args)
    {
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        Photo photo = (PhotoImpl)ac.getBean("photo");
        User a = (UserImpl)ac.getBean("user");
        /*UserEntity al = new UserEntity();
        al.setId("2201604711");
        al.setUsername("ZZZ");
        al.setPassword("123456");
        al.setRankFlag(3);
        al.setStatus("党支部");
        al.setSector("第一党支部");
        al.setSex("女");
        al.setInstitute("软件学院");
        al.setGrade("软件161班");
        al.setBirthday("2018-9");*/
        /*String flag = user.getUser("2201604793", 2);*/
        photo.update(1, 1, "ZZZ");
        /*a.update("2201604793", 12, "987654321");*/
        System.out.println(photo.getMaxId());
    }
}
