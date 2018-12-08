package com.zhdj.dao.part;

import com.zhdj.service.ActivityPart;
import com.zhdj.service.ActivityPartImpl;
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

@WebServlet(name = "ActivityPartCheckServlet", urlPatterns = "/activitypartcheck")
public class ActivityPartCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityPart activitypart = (ActivityPartImpl)ac.getBean("activityPart");
        User user = (UserImpl)ac.getBean("user");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String activityId = request.getParameter("activityId");
        int id = Integer.parseInt(activityId);
        String jsonMessage = activitypart.listForOne(id);
        if(jsonMessage.equals("[")){
            out.append("{\"flags\":\"1\"}");
        }else{
            out.append(jsonMessage);
            System.out.print(jsonMessage);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
