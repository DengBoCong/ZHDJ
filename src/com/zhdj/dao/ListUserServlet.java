package com.zhdj.dao;

import com.zhdj.service.User;
import com.zhdj.service.UserImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ListUserServlet", urlPatterns = "/listuser")
public class ListUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (UserImpl)ac.getBean("user");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String jsonMessage = "";
        if(user.getRankFlag(id) == 1){
            jsonMessage =user.listMember();
            if(jsonMessage.equals("[")) out.append("{\"flag\":\"1\"}");
            else out.append(jsonMessage);
        }else{
            jsonMessage = user.listSector(user.getUser(id, 5));
            if(jsonMessage.equals("[")) out.append("{\"flag\":\"1\"}");
            else out.append(jsonMessage);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
