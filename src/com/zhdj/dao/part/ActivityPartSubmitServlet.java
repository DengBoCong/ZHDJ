package com.zhdj.dao.part;

import com.zhdj.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ActivityPartSubmitServlet", urlPatterns = "/activitypartsubmit")
public class ActivityPartSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityBanner activityBanner = (ActivityBannerImpl)ac.getBean("activityBanner");
        ActivityPart activityPart = (ActivityPartImpl)ac.getBean("activityPart");
        User user = (UserImpl)ac.getBean("user");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String postId = request.getParameter("postId");
        String userId = request.getParameter("id");
        int flag = activityBanner.getFlag(Integer.parseInt(postId));
        int submit = activityBanner.getSubmitFlag(Integer.parseInt(postId));
        if(user.getUser(userId, 5).equals("毕业生")){
            out.append("{\"flags\":\"7\"}");
        }else if(activityPart.getActivityPart(userId + "_" + postId, 1).equals("s") && flag == 3){
            out.append("{\"flag\":\"6\"}");
        }else if(flag == 1){
            out.append("{\"flag\":\"1\"}");
        }else if(submit == 1){
            out.append("{\"flag\":\"2\"}");
        }else if(submit == 2){
            out.append("{\"flag\":\"3\"}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
