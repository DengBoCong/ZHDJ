package com.zhdj.dao.file;

import com.zhdj.entity.ActivitybannerEntity;
import com.zhdj.service.ActivityBanner;
import com.zhdj.service.ActivityBannerImpl;
import com.zhdj.service.User;
import com.zhdj.service.UserImpl;
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

@WebServlet(name = "MessageUploadServlet", urlPatterns = "/messageupload")
public class MessageUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String serverPath = "C:/Program Files/Tomcat 9.0/webapps/ZHEDJ/message";
    private String content = "";
    private String title = "";
    private String flag = "";
    private String time = "";
    private String username = "";
    private String author = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (UserImpl)ac.getBean("user");
        ActivityBanner activityBanner = (ActivityBannerImpl)ac.getBean("activityBanner");
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
                    if(value.equals("content")){
                        content = info;
                    }else if(value.equals("time")){
                        time = info;
                    }else if(value.equals("title")){
                        title = info;
                    }else if(value.equals("flag")){
                        flag = info;
                    }else {
                        author = info;
                        username = user.getUser(author, 2);
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
                    System.out.println(flag);
                    if(flag.equals("1")){
                        if(activityBanner.getActivity(1, 1).equals("s")){
                            ActivitybannerEntity activitybannerEntity = new ActivitybannerEntity();
                            activitybannerEntity.setCover(VariableUtils.Path + "ZHEDJ/message"+ "/" + 1 + "/" + 1 + name);
                            activitybannerEntity.setPartNum(0);
                            activitybannerEntity.setTitle(title);
                            activitybannerEntity.setAuthorName(username);
                            activitybannerEntity.setContent(content);
                            activitybannerEntity.setPublishedAt(time);
                            activitybannerEntity.setPostId(1);
                            activitybannerEntity.setSummary(title);
                            activitybannerEntity.setId(1);
                            activitybannerEntity.setType("banner");
                            activitybannerEntity.setSubmitDeadline("");
                            activitybannerEntity.setPartDeadline("");
                            activitybannerEntity.setFlag(0);
                            activityBanner.add(activitybannerEntity);
                        }else{
                            activityBanner.update(1, 1, username);
                            activityBanner.update(1, 2, title);
                            activityBanner.update(1, 3, content);
                            activityBanner.update(1, 4, VariableUtils.Path + "ZHEDJ/message"+ "/" + 1 + "/" + 1 + name);
                            activityBanner.update(1, 5, time);
                            activityBanner.update(1, 6, title);
                        }
                        FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" + 1 + "/" + 1 + name));
                    }else{
                        ActivitybannerEntity activitybannerEntity = new ActivitybannerEntity();
                        activitybannerEntity.setCover(VariableUtils.Path + "ZHEDJ/message"+ "/" + maxid + "/" + maxid + name);
                        activitybannerEntity.setPartNum(0);
                        activitybannerEntity.setTitle(title);
                        activitybannerEntity.setAuthorName(user.getUser(author, 2));
                        activitybannerEntity.setContent(content);
                        activitybannerEntity.setPublishedAt(time);
                        activitybannerEntity.setPostId(maxid);
                        activitybannerEntity.setSummary(title);
                        activitybannerEntity.setId(maxid);
                        activitybannerEntity.setType("message");
                        activitybannerEntity.setSubmitDeadline("");
                        activitybannerEntity.setPartDeadline("");
                        activitybannerEntity.setFlag(0);
                        activityBanner.add(activitybannerEntity);

                        FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" + maxid + "/" + maxid + name));
                    }
                    response.getWriter().append("公告发布成功");
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
