package com.zhdj.dao.file;

import com.zhdj.entity.PhotoEntity;
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

@WebServlet(name = "PhotoUploadServlet", urlPatterns = "/photoupload")
public class PhotoUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String serverPath = "C:/Program Files/Tomcat 9.0/webapps/ZHEDJ/photo";
    private String folderId = "";
    private String userId = "";
    private String time = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (UserImpl)ac.getBean("user");
        Photo photo = (PhotoImpl)ac.getBean("photo");
        PhotoEntity photoEntity = new PhotoEntity();
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
                    if(value.equals("photoFolderId")){
                        folderId = info;
                        System.out.println(folderId);
                        photoEntity.setPhotoOwner(folderId);
                    }else if(value.equals("userId")){
                        userId = info;
                        photoEntity.setPhotoAuthor(user.getUser(userId, 2));
                        photoEntity.setAuthorCover(user.getUser(userId, 16));
                        photoEntity.setPhotoRead(0);
                        photoEntity.setPhotoGood(0);
                        photoEntity.setPhotoId(photo.getMaxId() + 1);
                    }else{
                        time = info;
                        photoEntity.setPhotoTime(time);
                    }
                    System.out.println("info:" + info + "filename:" + value);
                } else { // >> 文件
                    File savePath = new File(serverPath + "/" + folderId + "/" + "contentImage");
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
                    photoEntity.setPhotoCover(VariableUtils.Path + "ZHEDJ/photo/" + folderId + "/" + "contentImage/" + name);
                    photo.add(photoEntity);
                    FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" + folderId + "/" + "contentImage/" + "/" + name));
                    response.getWriter().append("照片上传成功");
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
