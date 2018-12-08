package com.zhdj.dao.file;

import com.zhdj.entity.ActivitybannerEntity;
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

@WebServlet(name = "ActivityUploadServlet", urlPatterns = "/activityupload")
public class ActivityUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String serverPath = "C:/Program Files/Tomcat 9.0/webapps/ZHEDJ/activity";
    private String flag = "";
    private String content = "";
    private String title = "";
    private String time = "";
    private String author = "";
    private String parttime = "";
    private String submitdeadline = "";
    private String submitFlag = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (UserImpl)ac.getBean("user");
        ActivityBanner activityBanner = (ActivityBannerImpl)ac.getBean("activityBanner");
        ActivitybannerEntity activitybannerEntity = new ActivitybannerEntity();
        // 1.创建DiskFileItemFactory对象，配置缓存用
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        // 2. 创建 ServletFileUpload对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        // 3. 设置文件名称编码
        servletFileUpload.setHeaderEncoding("utf-8");
        // 4. 开始解析文件
        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : items) {

                if (fileItem.isFormField()) { // >> 普通数据
                    String info = fileItem.getString("utf-8");
                    String value = fileItem.getFieldName();
                    if(value.equals("flag")){
                        flag = info;
                        activitybannerEntity.setFlag(Integer.parseInt(flag));
                    }else if(value.equals("content")){
                        content = info;
                        activitybannerEntity.setContent(content);
                    }else if(value.equals("time")){
                        time = info;
                        activitybannerEntity.setPublishedAt(time);
                    }else if(value.equals("title")){
                        title = info;
                        activitybannerEntity.setTitle(title);
                    }else if(value.equals("parttime")){
                        parttime = info;
                        activitybannerEntity.setPartDeadline(parttime);
                    }else if(value.equals("submitdeadline")){
                        submitdeadline = info;
                        activitybannerEntity.setSubmitDeadline(submitdeadline);
                    }else if(value.equals("submitflag")){
                        submitFlag = info;
                        activitybannerEntity.setSubmitFlag(Integer.parseInt(submitFlag));
                    }else {
                        author = info;
                        activitybannerEntity.setAuthorName(user.getUser(author, 2));
                    }
                } else { // >> 文件
                    int maxid = activityBanner.getMaxId() + 1;
                    File savePath = new File(serverPath + "/" + maxid);
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
                    activitybannerEntity.setCover(VariableUtils.Path + "ZHEDJ/activity"+ "/" + maxid + "/" + maxid + name);
                    activitybannerEntity.setPartNum(0);
                    activitybannerEntity.setPostId(maxid);
                    activitybannerEntity.setSummary(title);
                    activitybannerEntity.setId(maxid);
                    activitybannerEntity.setType("activity");
                    activityBanner.add(activitybannerEntity);
                    FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" + maxid + "/" + maxid + name));
                    response.getWriter().append("活动发布成功");
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
