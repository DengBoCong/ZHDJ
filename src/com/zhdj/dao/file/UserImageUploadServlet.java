package com.zhdj.dao.file;

import com.zhdj.entity.PhotoFolderEntity;
import com.zhdj.service.PhotoFolder;
import com.zhdj.service.PhotoFolderImpl;
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

@WebServlet(name = "UserImageUploadServlet", urlPatterns = "/userimageupload")
public class UserImageUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String serverPath = "C:/Program Files/Tomcat 9.0/webapps/ZHEDJ/photo";
    private String id = "";
    private String descript = "";
    private String time = "";
    private String author = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.getWriter().append("served at: ").append(request.getContextPath());*/
        /*System.out.println(request.getContextPath());
        System.out.println("进入后台");*/
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=utf-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        PhotoFolder photoFolder = (PhotoFolderImpl)ac.getBean("photoFolder") ;
        User user = (UserImpl)ac.getBean("user");
        PhotoFolderEntity photoFolderEntity = new PhotoFolderEntity();
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
                    if(value.equals("id")){
                        id = info;
                        System.out.println(id);
                        if(photoFolder.getPhotoFolder(id, 1).equals("f"));
                        else{
                            response.getWriter().append("相册名已存在，添加失败");
                            return;
                        }
                        System.out.println(photoFolder.getPhotoFolder(id, 1));
                        photoFolderEntity.setId(id);
                    }else if(value.equals("descript")){
                        descript = info;
                        photoFolderEntity.setDescripe(descript);
                    }else if(value.equals("time")){
                        time = info;
                        photoFolderEntity.setDate(time);
                    }else {
                        author = info;
                        photoFolderEntity.setAuthor(user.getUser(author, 2));
                        photoFolderEntity.setCover(user.getUser(author, 16));
                    }
                } else { // >> 文件
                    File savePath = new File(serverPath + "/" + id);
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
                    photoFolderEntity.setCover(VariableUtils.Path + "ZHEDJ/photo/" + id + "/" + name);
                    photoFolder.add(photoFolderEntity);
                    FileUtils.copyInputStreamToFile(is, new File(serverPath + "/" + id + "/" + name));
                    response.getWriter().append("相册创建成功");
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
