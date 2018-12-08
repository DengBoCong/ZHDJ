package com.zhdj.dao.file;

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

@WebServlet(name = "PhotoReadServlet", urlPatterns = "/photoread")
public class PhotoReadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Photo photo = (PhotoImpl)ac.getBean("photo");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String flag = request.getParameter("flag");
        String id = request.getParameter("id");
        int photoId = Integer.parseInt(id);
        int read = 0;
        String jsonMessage = "";
        if(flag.equals("1")){
            read = photo.getPhotoRead(photoId) + 1;
            photo.updateRead(photoId, read);
        }else{
            out.append("{\"flags\":\"0\"}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
