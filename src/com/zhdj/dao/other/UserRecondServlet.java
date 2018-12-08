package com.zhdj.dao.other;

import com.zhdj.service.UserRecond;
import com.zhdj.service.UserRecondImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserRecondServlet", urlPatterns = "/userrecond")
public class UserRecondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserRecond userRecond = (UserRecondImpl)ac.getBean("userRecond");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("userId");
        String jsonMessage = userRecond.listUser(id);
        if(jsonMessage.equals("[")){
            out.append("{\"flags\":\"1\"}");
        }else{
            out.append(jsonMessage);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
