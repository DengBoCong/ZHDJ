package com.zhdj.dao;

import com.zhdj.entity.ActivitybannerEntity;
import com.zhdj.service.ActivityBanner;
import com.zhdj.service.ActivityBannerImpl;
import com.zhdj.service.User;
import com.zhdj.service.UserImpl;
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

@WebServlet(name = "AddUserServlet", urlPatterns = "/adduser")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String serverPath = "C:/Program Files/Tomcat 9.0/webapps/ZHEDJ/activity";
    private String flag = "";
    private String content = "";
    private String title = "";
    private String time = "";
    private String author = "";
    private String parttime = "";
    private String submitdeadline = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (UserImpl)ac.getBean("user");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();*/
        /*String id = request.getParameter("account");
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
        String id_number = request.getParameter("id_number");*/
        /*String flag = request.getParameter("content");
        String title = request.getParameter("title");
        String time = request.getParameter("time");
        String p = request.getParameter("logo");
        System.out.println(p);
        System.out.println(flag);
        System.out.println(title);
        System.out.println(time);*/
        /*user.update(id, 1, username);
        user.update(id, 5, sex);
        user.update(id, 6, institute);
        user.update(id, 7, grade);
        user.update(id, 8, birthday);
        user.update(id, 9, nation);
        user.update(id, 10, partytime);
        user.update(id, 11, qq);
        user.update(id, 12, phone);
        user.update(id, 13, email);
        user.update(id, 14, id_number);*/
        /*String jsonMessage = "{\"flag\":\"1\"}";
        out.append(jsonMessage);*/
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
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
                        System.out.println(flag);
                    }else if(value.equals("content")){
                        content = info;
                        System.out.println(content);
                    }else if(value.equals("time")){
                        time = info;
                        System.out.println(time);
                    }else if(value.equals("title")){
                        title = info;
                        System.out.println(title);
                    }else if(value.equals("parttime")){
                        parttime = info;
                        System.out.println(parttime);
                    }else if(value.equals("submitdeadline")){
                        submitdeadline = info;
                    }else {
                        author = info;
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
                    FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" + maxid + "/" + maxid + name));
                    /*response.getWriter().append("活动发布成功");*/
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
