package com.zhdj.dao;

import com.zhdj.entity.UserEntity;
import com.zhdj.entity.UserRankEntity;
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

@WebServlet(name = "UserAlertServlet", urlPatterns = "/useralert")
public class UserAlertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (UserImpl)ac.getBean("user");
        UserRecond userRecond = (UserRecondImpl)ac.getBean("userRecond");
        UserRank Rank = (UserRankImpl)ac.getBean("userRank");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String userId = request.getParameter("user_id");
        String userRank = request.getParameter("user_rank");
        String userStatus = request.getParameter("user_status");
        String userSector = request.getParameter("user_sector");
        String userAccount = request.getParameter("account");
        String userPassword = request.getParameter("password");
        String flag = request.getParameter("op_flag");
        String time = request.getParameter("time");

        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String institute = request.getParameter("institute");
        String grade = request.getParameter("grade");
        String birthday = request.getParameter("birthday");
        String nation = request.getParameter("nation");
        String partytime = request.getParameter("partytime");
        String qq = request.getParameter("qq");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String id_number = request.getParameter("id_number");
        String partuser = request.getParameter("partuser");
        String trainuser = request.getParameter("trainuser");

        int rank = 0;
        if(userRank.equals("党委账号")) rank = 1;
        else if(userRank.equals("支部账号")) rank = 2;
        else rank = 3;
        if(rank == 0) out.append("{\"flag\":\"0\"}");
        else{
            if(flag.equals("1")){
                user.updageRankFlag(userId, rank);
                user.update(userId, 3, userStatus);
                user.update(userId, 4, userSector);
                user.update(userId, 1, username);
                user.update(userId, 2, userPassword);
                user.update(userId, 5, sex);
                user.update(userId, 6, institute);
                user.update(userId, 7, grade);
                user.update(userId, 8, birthday);
                user.update(userId, 9, nation);
                user.update(userId, 10, partytime);
                user.update(userId, 11, qq);
                user.update(userId, 12, phone);
                user.update(userId, 13, email);
                user.update(userId, 14, id_number);
                user.update(userId, 16, partuser);
                user.update(userId, 17, trainuser);
                UserRecondEntity userRecondEntity = new UserRecondEntity();
                userRecondEntity.setUserid(userId);
                userRecondEntity.setUsername(user.getUser(userId, 2));
                userRecondEntity.setRecondTime(time);
                userRecondEntity.setRecondFlag(userRecond.getMaxId() + 1);
                userRecondEntity.setRecondContent("你账号属性于" + time + "，被修改，如有疑问，请联系党支部书记或党委。");
                userRecond.add(userRecondEntity);
                String jsonMessage = "{\"flag\":\"1\"}";
                out.append(jsonMessage);
            }else{
                if(user.getUser(userAccount,1) != ""){
                    out.append("{\"flag\":\"3\"}");
                }else{
                    UserEntity userEntity = new UserEntity();
                    userEntity.setId(userAccount);
                    userEntity.setPassword(userPassword);
                    userEntity.setRankFlag(rank);
                    userEntity.setStatus(userStatus);
                    userEntity.setSector(userSector);
                    userEntity.setImage("");
                    userEntity.setBirthday(birthday);
                    userEntity.setGrade(grade);
                    userEntity.setInstitute(institute);
                    userEntity.setSex(sex);
                    userEntity.setUsername(username);
                    userEntity.setEmail(email);
                    userEntity.setIdNumber(id_number);
                    userEntity.setNation(nation);
                    userEntity.setPhone(phone);
                    userEntity.setPartytime(partytime);
                    userEntity.setQq(qq);
                    userEntity.setPartUser(partuser);
                    userEntity.setTrainUser(trainuser);
                    user.add(userEntity);
                    UserRecondEntity userRecondEntity = new UserRecondEntity();
                    userRecondEntity.setUserid(userAccount);
                    userRecondEntity.setUsername(username);
                    userRecondEntity.setRecondTime(time);
                    userRecondEntity.setRecondFlag(userRecond.getMaxId() + 1);
                    userRecondEntity.setRecondContent("你账号于" + time + "被创建，正式开启你的智慧党建之旅。");
                    userRecond.add(userRecondEntity);
                    UserRankEntity userRankEntity = new UserRankEntity();
                    userRankEntity.setRankSourceold(0);
                    userRankEntity.setRankSource(0);
                    userRankEntity.setSourceStituationfirst("暂无加分记录");
                    userRankEntity.setUserid(userAccount);
                    userRankEntity.setUsername(username);
                    Rank.add(userRankEntity);
                    String jsonMessage = "{\"flag\":\"1\"}";
                    out.append(jsonMessage);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public String _format(int number){
        return (number < 10 ? ("0" + number) : "" + number);
    }
}
