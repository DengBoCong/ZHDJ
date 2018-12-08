package com.zhdj.service;

import com.zhdj.entity.FileEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Iterator;
import java.util.List;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-26 17:22
 **/
public class FileSubmitImpl implements FileSubmit {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(FileEntity fileEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FileEntity fileEntity1 = null;
        fileEntity1 = (FileEntity)session.get(FileEntity.class, fileEntity.getFileUserid());
        if(fileEntity1 != null){
            return false;
        }
        session.save(fileEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String FileUserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FileEntity fileEntity = null;
        fileEntity = (FileEntity)session.get(FileEntity.class, FileUserId);
        if(fileEntity == null){
            return false;
        }
        session.delete(fileEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String FileUserId, int flag, String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FileEntity fileEntity = null;
        fileEntity = (FileEntity)session.get(FileEntity.class, FileUserId);
        if(fileEntity == null){
            return false;
        }
        switch (flag){
            case 1: fileEntity.setFileName(s);break;
            case 2: fileEntity.setFileSubmittime(s);break;
            case 3: fileEntity.setFileUrl(s);break;
            case 4: fileEntity.setFileUserid(s);break;
            case 5: fileEntity.setFileUsername(s);break;
        }
        session.update(fileEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateFileFlag(String FileUserId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FileEntity fileEntity = null;
        fileEntity = (FileEntity)session.get(FileEntity.class, FileUserId);
        if(fileEntity == null){
            return false;
        }
        fileEntity.setFileFlag(num);
        session.update(fileEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String getFile(String FileUserId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FileEntity fileEntity = null;
        fileEntity = (FileEntity)session.get(FileEntity.class, FileUserId);
        if(fileEntity == null){
            return "f";
        }
        switch (flag){
            case 1: return fileEntity.getFileUserid();
            case 2: return fileEntity.getFileName();
            case 3: return fileEntity.getFileSubmittime();
            case 4: return fileEntity.getFileUrl();
            case 5: return fileEntity.getFileUsername();
        }
        return "f";
    }

    @Override
    public int getFileFlag(String FileUserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FileEntity fileEntity = null;
        fileEntity = (FileEntity)session.get(FileEntity.class, FileUserId);
        if(fileEntity == null){
            return 0;
        }
        return fileEntity.getFileFlag();
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM FileEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            FileEntity fileEntity = (FileEntity)iterator.next();
            temp = temp + "{\"file_userid\":\"" + fileEntity.getFileUserid() + "\"";
            temp = temp + ",\"file_flag\":\"" + fileEntity.getFileFlag() + "\"";
            temp = temp + ",\"file_url\":\"" + fileEntity.getFileUrl() + "\"";
            temp = temp + ",\"file_submittime\":\"" + fileEntity.getFileSubmittime() + "\"";
            temp = temp + ",\"file_username\":\"" + fileEntity.getFileUsername() + "\"";
            temp = temp + ",\"file_id\":\"" + fileEntity.getFileId() + "\"";
            temp = temp + ",\"file_name\":\"" + fileEntity.getFileName() + "\"}";
            if(count == 0) temp = temp + "]";
            else temp = temp + ",";
            count++;
            listJson = temp + listJson;
        }
        listJson = "[" + listJson;
        transaction.commit();
        session.close();
        return listJson;
    }

    @Override
    public String listItem(String FileUserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FileEntity fileEntity = null;
        fileEntity = (FileEntity)session.get(FileEntity.class, FileUserId);
        String temp = "";
        temp = temp + "{\"file_userid\":\"" + fileEntity.getFileUserid() + "\"";
        temp = temp + ",\"file_flag\":\"" + fileEntity.getFileFlag() + "\"";
        temp = temp + ",\"file_url\":\"" + fileEntity.getFileUrl() + "\"";
        temp = temp + ",\"file_submittime\":\"" + fileEntity.getFileSubmittime() + "\"";
        temp = temp + ",\"file_username\":\"" + fileEntity.getFileUsername() + "\"";
        temp = temp + ",\"file_id\":\"" + fileEntity.getFileId() + "\"";
        temp = temp + ",\"file_name\":\"" + fileEntity.getFileName() + "\"}";
        transaction.commit();
        session.close();
        return temp;
    }

    @Override
    public int itemCount(int FileFlag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("FROM FileEntity r WHERE r.fileFlag = " + FileFlag).list();
        if(list == null){
            return 0;
        }
        return list.size();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
