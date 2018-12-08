package com.zhdj.dao;

import com.zhdj.service.User;
import com.zhdj.service.UserImpl;
import com.zhdj.service.UserRank;
import com.zhdj.service.UserRankImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/deleteuser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (UserImpl)ac.getBean("user");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String checkIdArray = request.getParameter("idArray");
        StringTokenizer stringTokenizer = new StringTokenizer(checkIdArray, ",");
        while (stringTokenizer.hasMoreTokens()){
            /*System.out.println(stringTokenizer.nextToken());*/
            user.delete(stringTokenizer.nextToken());
        }
        String jsonMessage = "{\"flag\":\"1\"}";
        out.append(jsonMessage);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
