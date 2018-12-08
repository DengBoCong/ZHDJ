package com.zhdj.dao;

import com.zhdj.service.User;
import com.zhdj.service.UserImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

@WebServlet(name = "UploadServlet", urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {
    private long fileSize;
    private long totalSize;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File saveFile = new File(savePath);
        // 上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tempFile = new File(tempPath);
        // 判断上传文件的保存目录是否存在
        if (!tempFile.exists()) {
            // 创建目录
            tempFile.mkdir();
        }
        // 上传消息提示
        String message = "";
        try {
            // 使用Apache文件上传组件处理文件上传步骤：
            // 1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            // 设置上传时生成的临时文件的保存目录
            factory.setRepository(tempFile);
            // 2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 监听文件上传进度
            upload.setProgressListener(new ProgressListener() {

                public void update(long arg0, long arg1, int arg2) {
                    System.out.println("文件大小为：" + arg1 + ",当前已处理：" + arg0);
                }
            });
            // 解决上传文件名的中文乱码
            upload.setHeaderEncoding("utf-8");
            // 3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                // 不是multipart/form-data方式提交
                return;
            }

            // 设置上传单个文件的大小的最大值，目前是设置为1024*1024*100字节，也就是100MB
            upload.setFileSizeMax(1024 * 1024 * 100);
            // 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为200MB
            upload.setSizeMax(1024 * 1024 * 100);

            // 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    // 普通数据项
                    String name = item.getFieldName();
                    String value = item.getString("utf-8");
                } else {
                    // 文件数据
                    // 文件名称

                    String fileName = item.getName();
                    if (fileName == null || "".equals(fileName.trim())) {
                        // 文件名为空,不保存数据
                        continue;
                    }
                    // 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，
                    // 如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    // 只保留文件名，并且区分不同系统的分隔符
                    System.out.println("上传的文件名为：" + fileName);
                    fileName = fileName.substring(fileName
                            .lastIndexOf(File.separator) + 1);
                    System.out.println("保存的文件名为：" + fileName);
                    // 得到上传文件的扩展名
                    String fileExtName = fileName.substring(fileName
                            .lastIndexOf(".") + 1);
                    // 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是：" + fileExtName);
                    // 获取item中的上传文件的输入流,以及文件输出流
                    InputStream is = item.getInputStream();
                    // 得到文件保存的名称
                    String saveFilename = makeFileName(fileName);
                    // 得到文件的保存目录
                    String realSavePath = makePath(saveFilename, savePath);
                    OutputStream os = new FileOutputStream(realSavePath + "\\"
                            + fileName);
                    // 以下是文件的保存
                    // 创建缓冲区
                    byte[] buffer = new byte[1024 * 5];
                    int len = 0;
                    while ((len = is.read(buffer)) > 0) {
                        os.write(buffer, 0, len);
                    }
                    is.close();
                    os.close();
                    // 删除处理文件上传时生成的临时文件
                    // item.delete();
                    message = "上传成功";
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "单个文件超出最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request,
                    response);
            return;
        } catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request,
                    response);
            return;
        } catch (Exception e) {
            message = "文件上传失败！";
            e.printStackTrace();
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @function 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @param fileName
     * @return
     */
    private String makeFileName(String fileName) {
        // 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    /**
     * 为防止一个目录下出现太多文件，使用hash算法打散存储
     *
     * @param filename
     * @param savePath
     * @return
     */
    private String makePath(String filename, String savePath) {
        // 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf; // 0--15
        int dir2 = (hashcode & 0xf0) >> 4; // 0-15
        // 构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3
        // upload\3\5
        // File既可以代表文件也可以代表目录
        File file = new File(dir);
        // 如果目录不存在
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
}
