package com.zhdj.dao;

import com.zhdj.service.Activity;
import com.zhdj.service.ActivityBanner;
import com.zhdj.service.ActivityBannerImpl;
import com.zhdj.service.ActivityImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ActivityBannerServlet", urlPatterns = "/activityBanner")
public class ActivityBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityBanner activity = (ActivityBannerImpl)ac.getBean("activityBanner");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.append(activity.listItem(1));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
