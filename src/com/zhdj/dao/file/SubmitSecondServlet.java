package com.zhdj.dao.file;

import com.zhdj.entity.ActivitypartEntity;
import com.zhdj.entity.FileEntity;
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

@WebServlet(name = "SubmitSecondServlet", urlPatterns = "/submitsecond")
public class SubmitSecondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        FileSubmit fileSubmit = (FileSubmitImpl)ac.getBean("filesubmit");
        User user = (UserImpl)ac.getBean("user");
        UserRank userRank = (UserRankImpl)ac.getBean("userRank");
        ActivityPart activityPart = (ActivityPartImpl)ac.getBean("activityPart");
        ActivityBanner activityBanner = (ActivityBannerImpl)ac.getBean("activityBanner");
        UserRecond userRecond = (UserRecondImpl)ac.getBean("userRecond");

        String id = request.getParameter("id");
        String Activityid = request.getParameter("Activityid");
        String time = request.getParameter("time");
        String content = request.getParameter("content");
        System.out.print(id);
        System.out.print(time);
        System.out.print(content);
        System.out.print(Activityid);
        String username = user.getUser(id, 2);

        if(fileSubmit.getFile(id + "_" + Activityid, 1).equals("f")){
            if(activityPart.getActivityPart(id + "_" + Activityid, 1).equals("s")){
                ActivitypartEntity activitypartEntity = new ActivitypartEntity();
                activitypartEntity.setPartSubmitTime(time);
                activitypartEntity.setPartId(Integer.parseInt(Activityid));
                activitypartEntity.setPartUsername(username);
                activitypartEntity.setPartFlag(id + "_" + Activityid);
                activitypartEntity.setPartFileurl(content);
                activitypartEntity.setPartSituation(1);
                activitypartEntity.setPartTime("无报名时间");
                activityPart.add(activitypartEntity);
            }else{
                activityPart.updateSituation(id + "_" + Activityid, 1);
                activityPart.update(id + "_" + Activityid, 1, content);
                activityPart.update(id + "_" + Activityid, 4, time);
            }
            UserRecondEntity userRecondEntity = new UserRecondEntity();
            userRecondEntity.setUserid(id);
            userRecondEntity.setUsername(username);
            userRecondEntity.setRecondFlag(userRecond.getMaxId() + 1);
            userRecondEntity.setRecondTime(time);
            userRecondEntity.setRecondContent("你于" + time + "成功提交 " + activityBanner.getActivity(Integer.parseInt(Activityid), 2) + " 的材料");
            userRecond.add(userRecondEntity);
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileUserid(id);
            fileEntity.setFileFlag(Integer.parseInt(Activityid));
            fileEntity.setFileId(id + "_" + Activityid);
            fileEntity.setFileUsername(username);
            fileEntity.setFileSubmittime(time);
            fileEntity.setFileName(username + "-" + id + "-提交材料");
            fileEntity.setFileUrl(content);
            fileSubmit.add(fileEntity);
            /*temp = userRank.getRankSource(id);
            userRank.updageRankSource(id,temp + 2);
            userRank.updageRankSourceOld(id, temp);
            userRank.update(id, 3, "成功提交 " + activityBanner.getActivity(Integer.parseInt(Activityid), 2) + " 活动材料，加2分");*/
        }else{
            fileSubmit.update(id + "_" + Activityid, 3, content);
            fileSubmit.update(id + "_" + Activityid, 2, time);
            activityPart.update(id + "_" + Activityid, 1, content);
            activityPart.update(id + "_" + Activityid, 4, time);
            /*fileSubmit.update(id + "_" + Activityid, 1, user.getUser(id, 2) + "-" + id + "-提交材料");*/
        }
        response.getWriter().append("{\"tip\":\"材料提交成功\"}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
