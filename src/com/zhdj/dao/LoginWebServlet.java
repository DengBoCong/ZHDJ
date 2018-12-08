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

@WebServlet(name = "LoginWebServlet", urlPatterns = "/loginweb")
public class LoginWebServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*ServletContext   servletContext   =   this.getServletContext();
        ApplicationContext   ac   =   WebApplicationContextUtils.getWebApplicationContext(servletContext);
        User user=(UserImpl)ac.getBean("user");*/
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (UserImpl)ac.getBean("user");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String flags = request.getParameter("flags");
        String id = request.getParameter("username");
        String password = request.getParameter("password");
        String password_used = request.getParameter("password_used");

        String jsonMessage = "{\"flag\":\"0\"}";
        if(flags.equals("1") && id != null){
            String loginPassword = user.getUser(id, 3);
            if(password.equals("") || !loginPassword.equals(password)){
                out.append(jsonMessage);
            }else if(user.getRankFlag(id) ==3){
                jsonMessage = "{\"flag\":\"2\"}";
                out.append(jsonMessage);
            }
            else{
                jsonMessage = "{\"flag\":\"1\"}";
                out.append(jsonMessage);
            }
        }
        if(flags.equals("2") && id != null){
            String alterPassword = user.getUser(id, 3);
            if(password_used.equals("") || !alterPassword.equals(password_used)){
                out.append(jsonMessage);
            }else{
                user.update(id, 2, password);
                jsonMessage = "{\"flag\":\"1\"}";
                out.append(jsonMessage);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
