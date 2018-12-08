package com.zhdj.dao.file;

import com.zhdj.entity.ActivitypartEntity;
import com.zhdj.entity.FileEntity;
import com.zhdj.entity.UserRecondEntity;
import com.zhdj.service.*;
import com.zhdj.utils.VariableUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "FileSubmitServlet", urlPatterns = "/filesubmit")
public class FileSubmitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String serverPath = "C:/Program Files/Tomcat 9.0/webapps/ZHEDJ/activity";
    private String id = "";
    private String Activityid = "";
    private String time = "";
    private String kind = "";
    private String username = "";
    private boolean flag = false;
    private int temp = 0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        FileSubmit fileSubmit = (FileSubmitImpl)ac.getBean("filesubmit");
        User user = (UserImpl)ac.getBean("user");
        UserRank userRank = (UserRankImpl)ac.getBean("userRank");
        ActivityPart activityPart = (ActivityPartImpl)ac.getBean("activityPart");
        ActivityBanner activityBanner = (ActivityBannerImpl)ac.getBean("activityBanner");
        UserRecond userRecond = (UserRecondImpl)ac.getBean("userRecond");
        FileEntity fileEntity = new FileEntity();
        // 1.创建DiskFileItemFactory对象，配置缓存用
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        // 2. 创建 ServletFileUpload对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        // 3. 设置文件名称编码
        servletFileUpload.setHeaderEncoding("utf-8");
        // 4. 开始解析文件
        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);
            System.out.println(items);
            for (FileItem fileItem : items) {
                if (fileItem.isFormField()) { // >> 普通数据
                    String info = fileItem.getString("utf-8");
                    String value = fileItem.getFieldName();
                    if(value.equals("id")){
                        id = info;
                        username = user.getUser(id, 2);
                    }else if(value.equals("Activityid")){
                        Activityid = info;
                    }else if(value.equals("kind")){
                        kind = info;
                    }else{
                        time = info;
                    }
                } else { // >> 文件
                    File savePath = new File(serverPath + "/" + Activityid + "/submit");
                    // 判断上传文件的保存目录是否存在
                    if (!savePath.exists()) {
                        // 创建目录
                        savePath.mkdir();
                    }
                    // 1. 获取文件名称
                    String name = fileItem.getName();
                    // 2. 获取文件的实际内容
                    InputStream is = fileItem.getInputStream();
                    // 3. 保存文件
                    if(fileSubmit.getFile(id + "_" + Activityid, 1).equals("f")){
                        if(activityPart.getActivityPart(id + "_" + Activityid, 1).equals("s")){
                            ActivitypartEntity activitypartEntity = new ActivitypartEntity();
                            activitypartEntity.setPartSubmitTime(time);
                            activitypartEntity.setPartId(Integer.parseInt(Activityid));
                            activitypartEntity.setPartUsername(username);
                            activitypartEntity.setPartFlag(id + "_" + Activityid);
                            activitypartEntity.setPartFileurl(VariableUtils.Path + "ZHEDJ/activity" + "/" + Activityid + "/submit" + "/" + user.getUser(id, 2) + "-" + id + kind);
                            activitypartEntity.setPartSituation(1);
                            activitypartEntity.setPartTime("无报名时间");
                            activityPart.add(activitypartEntity);
                        }else{
                            activityPart.updateSituation(id + "_" + Activityid, 1);
                            activityPart.update(id + "_" + Activityid, 1, VariableUtils.Path + "ZHEDJ/activity" + "/" + Activityid + "/submit" + "/" + username + "-" + id + kind);
                            activityPart.update(id + "_" + Activityid, 4, time);
                        }
                        UserRecondEntity userRecondEntity = new UserRecondEntity();
                        userRecondEntity.setUserid(id);
                        userRecondEntity.setUsername(username);
                        userRecondEntity.setRecondFlag(userRecond.getMaxId() + 1);
                        userRecondEntity.setRecondTime(time);
                        userRecondEntity.setRecondContent("你于" + time + "成功提交 " + activityBanner.getActivity(Integer.parseInt(Activityid), 2) + " 的材料");
                        userRecond.add(userRecondEntity);
                        fileEntity.setFileUserid(id);
                        fileEntity.setFileFlag(Integer.parseInt(Activityid));
                        fileEntity.setFileId(id + "_" + Activityid);
                        fileEntity.setFileUsername(username);
                        fileEntity.setFileSubmittime(time);
                        fileEntity.setFileName(username + "-" + id + "-提交材料");
                        fileEntity.setFileUrl(VariableUtils.Path + "ZHEDJ/activity" + "/" + Activityid + "/submit" + "/" + username + "-" + id + kind);
                        fileSubmit.add(fileEntity);
                        /*temp = userRank.getRankSource(id);
                        userRank.updageRankSource(id,temp + 2);
                        userRank.updageRankSourceOld(id, temp);
                        userRank.update(id, 3, "成功提交 " + activityBanner.getActivity(Integer.parseInt(Activityid), 2) + " 活动材料，加2分");*/
                    }else{
                        fileSubmit.update(id + "_" + Activityid, 3, VariableUtils.Path + "ZHEDJ/activity" + "/" + Activityid + "/submit" + "/" + username + "-" + id + kind);
                        fileSubmit.update(id + "_" + Activityid, 2, time);
                        activityPart.update(id + "_" + Activityid, 1, VariableUtils.Path + "ZHEDJ/activity" + "/" + Activityid + "/submit" + "/" + username + "-" + id + kind);
                        activityPart.update(id + "_" + Activityid, 4, time);
                        /*fileSubmit.update(id + "_" + Activityid, 1, user.getUser(id, 2) + "-" + id + "-提交材料");*/
                    }
                    FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" + Activityid + "/submit" + "/" + username + "-" + id + kind));
                    response.getWriter().append("材料提交成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
