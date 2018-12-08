package com.zhdj.dao;

import com.zhdj.service.Photo;
import com.zhdj.service.PhotoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PhotoServlet", urlPatterns = "/photo")
public class PhotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Photo photo = (PhotoImpl)ac.getBean("photo");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String flags = request.getParameter("flag");
        String photoId = request.getParameter("id");
        int id = Integer.parseInt(photoId);
        if(flags.equals("1")){
            if(photo.delete(id)) out.append("{\"flag\":\"1\"}");
            else out.append("{\"flag\":\"0\"}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
