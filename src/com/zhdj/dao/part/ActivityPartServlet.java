package com.zhdj.dao.part;

import com.zhdj.entity.ActivitypartEntity;
import com.zhdj.entity.UserRecondEntity;
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

@WebServlet(name = "ActivityPartServlet", urlPatterns = "/activitypart")
public class ActivityPartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityPart activitypart = (ActivityPartImpl)ac.getBean("activityPart");
        UserRecond userRecond = (UserRecondImpl)ac.getBean("userRecond");
        UserRank userRank = (UserRankImpl)ac.getBean("userRank");
        User user = (UserImpl)ac.getBean("user");
        ActivityBanner activityBanner = (ActivityBannerImpl)ac.getBean("activityBanner");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String postId = request.getParameter("postId");
        String userId = request.getParameter("userId");
        String partTime = request.getParameter("partTime");
        String username = user.getUser(userId, 2);
        if(user.getUser(userId, 5).equals("毕业生")){
            out.append("{\"flags\":\"你已经毕业，不允许报名\"}");
        }
        else if(activityBanner.getFlag(Integer.parseInt(postId)) == 2){
            out.append("{\"flags\":\"该活动不允许报名\"}");
        }
        else if(activitypart.getActivityPart(userId + "_" + postId, 1).equals("s")){
            ActivitypartEntity activitypartEntity = new ActivitypartEntity();
            activitypartEntity.setPartId(Integer.parseInt(postId));
            activitypartEntity.setPartUsername(username);
            activitypartEntity.setPartFlag(userId + "_" + postId);
            activitypartEntity.setPartTime(partTime);
            activitypartEntity.setPartSubmitTime("暂无提交");
            activitypartEntity.setPartSituation(0);
            activitypartEntity.setPartFileurl("暂无提交");
            activitypart.add(activitypartEntity);
            UserRecondEntity userRecondEntity = new UserRecondEntity();
            userRecondEntity.setUserid(userId);
            userRecondEntity.setUsername(username);
            userRecondEntity.setRecondFlag(userRecond.getMaxId() + 1);
            userRecondEntity.setRecondTime(partTime);
            userRecondEntity.setRecondContent("你于 " + partTime + " 成功报名参加 "
                    + activityBanner.getActivity(Integer.parseInt(postId), 2) + " 的活动");
            userRecond.add(userRecondEntity);
            System.out.print("--------");
            /*int temp = userRank.getRankSource(userId);*/
            /*userRank.updageRankSource(userId,temp + 1);*/
            /*userRank.updageRankSourceOld(userId, temp);*/
            /*userRank.update(userId, 3, "成功报名 " + activityBanner.getActivity(Integer.parseInt(postId), 2) + " 活动，加1分");*/
            System.out.print("++++++++++");
            out.append("{\"flags\":\"你已成功报名\"}");
        }else{
            out.append("{\"flags\":\"你已报名该活动，请勿重复报名\"}");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
