package com.zhdj.dao;

import com.zhdj.service.PhotoFolder;
import com.zhdj.service.PhotoFolderImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PhotoFolderServlet", urlPatterns = "/photofolder")
public class PhotoFolderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        PhotoFolder photoFolder = (PhotoFolderImpl)ac.getBean("photoFolder");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String flags = request.getParameter("flags");
        String photoFolderId = request.getParameter("PhotoFolderId");
        if(flags.equals("1")){
            if(photoFolder.delete(photoFolderId)) out.append("{\"flag\":\"1\"}");
            else out.append("{\"flag\":\"0\"}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
