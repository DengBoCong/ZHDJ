package com.zhdj.service;

import com.zhdj.entity.UserRecondEntity;
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
 * @create: 2018-08-22 11:17
 **/
public class UserRecondImpl implements UserRecond {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(UserRecondEntity userRecondEntity) {
        Session session = sessionFactory.openSession();
        Transaction tx= session.beginTransaction();
        UserRecondEntity userRecondEntity1 = null;
        userRecondEntity1 = (UserRecondEntity)session.get(UserRecondEntity.class, userRecondEntity.getRecondFlag());
        if(userRecondEntity1 != null){
            return false;
        }
        session.save(userRecondEntity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String UserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRecondEntity userRecondEntity = null;
        userRecondEntity = (UserRecondEntity)session.get(UserRecondEntity.class, UserId);
        if(userRecondEntity == null){
            return false;
        }
        session.delete(userRecondEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String UserId, int flag, String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRecondEntity userRecondEntity = null;
        userRecondEntity = (UserRecondEntity)session.get(UserRecondEntity.class, UserId);
        if(userRecondEntity == null){
            return false;
        }
        switch (flag){
            case 1: userRecondEntity.setUserid(s);break;
            case 2: userRecondEntity.setUsername(s);break;
            case 3: userRecondEntity.setRecondContent(s);break;
            case 4: userRecondEntity.setRecondTime(s);break;
        }
        session.update(userRecondEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updageRecondFlag(String UserId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRecondEntity userRecondEntity = null;
        userRecondEntity = (UserRecondEntity)session.get(UserRecondEntity.class, UserId);
        if(userRecondEntity == null){
            return false;
        }
        userRecondEntity.setRecondFlag(flag);
        session.update(userRecondEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String getUserRecond(String UserId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRecondEntity userRecondEntity = null;
        userRecondEntity = (UserRecondEntity)session.get(UserRecondEntity.class, UserId);
        if(userRecondEntity == null){
            return "";
        }
        switch (num){
            case 1: return userRecondEntity.getRecondContent();
            case 2: return userRecondEntity.getRecondTime();
            case 3: return userRecondEntity.getUsername();
            case 4:return  userRecondEntity.getUserid();
        }
        return "s";
    }

    @Override
    public int getRecondFlag(String UserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRecondEntity userRecondEntity = null;
        userRecondEntity = (UserRecondEntity)session.get(UserRecondEntity.class, UserId);
        if(userRecondEntity == null){
            return 0;
        }
        return userRecondEntity.getRecondFlag();
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List users = session.createQuery("FROM UserRecondEntity ").list();
        for(Iterator iterator = users.iterator(); iterator.hasNext();){
            String temp = "";
            UserRecondEntity userRecondEntity = (UserRecondEntity)iterator.next();
            temp = temp + "{\"userid\":\"" + userRecondEntity.getUserid() + "\"";
            temp = temp + ",\"recond_flag\":\"" + userRecondEntity.getRecondFlag() + "\"";
            temp = temp + ",\"recond_time\":\"" + userRecondEntity.getRecondTime() + "\"";
            temp = temp + ",\"recond_content\":\"" + userRecondEntity.getRecondContent() + "\"";
            temp = temp + ",\"username\":\"" + userRecondEntity.getUsername() + "\"}";
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
    public String listUser(String UserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List users = session.createQuery("FROM UserRecondEntity ").list();
        for(Iterator iterator = users.iterator(); iterator.hasNext();){
            String temp = "";
            UserRecondEntity userRecondEntity = (UserRecondEntity)iterator.next();
            if(!userRecondEntity.getUserid().equals(UserId)) continue;
            temp = temp + "{\"userid\":\"" + userRecondEntity.getUserid() + "\"";
            temp = temp + ",\"recond_flag\":\"" + userRecondEntity.getRecondFlag() + "\"";
            temp = temp + ",\"recond_time\":\"" + userRecondEntity.getRecondTime() + "\"";
            temp = temp + ",\"recond_content\":\"" + userRecondEntity.getRecondContent() + "\"";
            temp = temp + ",\"username\":\"" + userRecondEntity.getUsername() + "\"}";
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
    public int getMaxId() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List photoList = session.createQuery("FROM UserRecondEntity r order by r.recondFlag desc ").list();
        if(photoList == null){
            return 0;
        }
        UserRecondEntity userRecondEntity = (UserRecondEntity)photoList.get(0);
        return userRecondEntity.getRecondFlag();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
