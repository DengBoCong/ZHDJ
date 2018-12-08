package com.zhdj.dao;

import com.zhdj.service.ActivityBanner;
import com.zhdj.service.ActivityBannerImpl;
import com.zhdj.service.Message;
import com.zhdj.service.MessageImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MessageListServlet", urlPatterns = "/messageList")
public class MessageListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityBanner message = (ActivityBannerImpl)ac.getBean("activityBanner");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String jsonMessage = message.listMessage();
        out.append(jsonMessage);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
