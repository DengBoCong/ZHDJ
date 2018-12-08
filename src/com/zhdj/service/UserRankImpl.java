package com.zhdj.service;

import com.zhdj.entity.UserRankEntity;
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
 * @create: 2018-08-21 15:39
 **/
public class UserRankImpl implements UserRank {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(UserRankEntity userRankEntity) {
        Session session = sessionFactory.openSession();
        Transaction tx= session.beginTransaction();
        UserRankEntity userRankEntity1 = null;
        userRankEntity1 = (UserRankEntity)session.get(UserRankEntity.class, userRankEntity.getUserid());
        if(userRankEntity1 != null){
            return false;
        }
        session.save(userRankEntity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String UserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRankEntity userRankEntity = null;
        userRankEntity = (UserRankEntity)session.get(UserRankEntity.class, UserId);
        if(userRankEntity == null){
            return false;
        }
        session.delete(userRankEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String UserId, int flag, String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRankEntity userRankEntity = null;
        userRankEntity = (UserRankEntity)session.get(UserRankEntity.class, UserId);
        if(userRankEntity == null){
            return false;
        }
        switch (flag){
            /*case 1: userEntity.setId(s);break;*/
            case 1: userRankEntity.setUserid(s);break;
            case 2: userRankEntity.setUsername(s);break;
            case 3: userRankEntity.setSourceStituationfirst(s);break;
        }
        session.update(userRankEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updageRankSource(String UserId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRankEntity userRankEntity = null;
        userRankEntity = (UserRankEntity)session.get(UserRankEntity.class, UserId);
        if(userRankEntity == null){
            return false;
        }
        userRankEntity.setRankSource(flag);
        session.update(userRankEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updageRankSourceOld(String UserId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRankEntity userRankEntity = null;
        userRankEntity = (UserRankEntity)session.get(UserRankEntity.class, UserId);
        if(userRankEntity == null){
            return false;
        }
        userRankEntity.setRankSourceold(flag);
        session.update(userRankEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String getUserRank(String UserId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRankEntity userRankEntity = null;
        userRankEntity = (UserRankEntity)session.get(UserRankEntity.class, UserId);
        if(userRankEntity == null){
            return "";
        }
        switch (num){
            case 1: return userRankEntity.getSourceStituationfirst();
            case 2: return userRankEntity.getUsername();
            case 3: return userRankEntity.getUserid();
        }
        return "s";

    }

    @Override
    public int getRankSource(String UserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRankEntity userRankEntity = null;
        userRankEntity = (UserRankEntity)session.get(UserRankEntity.class, UserId);
        if(userRankEntity == null){
            return 0;
        }
        return userRankEntity.getRankSource();
    }

    @Override
    public int getRankSourceOld(String UserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRankEntity userRankEntity = null;
        userRankEntity = (UserRankEntity)session.get(UserRankEntity.class, UserId);
        if(userRankEntity == null){
            return 0;
        }
        return userRankEntity.getRankSourceold();
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List users = session.createQuery("FROM UserRankEntity r order by r.rankSource asc ").list();
        for(Iterator iterator = users.iterator(); iterator.hasNext();){
            String temp = "";
            UserRankEntity userRankEntity = (UserRankEntity)iterator.next();
            temp = temp + "{\"userid\":\"" + userRankEntity.getUserid() + "\"";
            temp = temp + ",\"rank_source\":\"" + userRankEntity.getRankSource() + "\"";
            temp = temp + ",\"source_stituationfirst\":\"" + userRankEntity.getSourceStituationfirst() + "\"";
            temp = temp + ",\"rank_sourceold\":\"" + userRankEntity.getRankSourceold() + "\"";
            temp = temp + ",\"username\":\"" + userRankEntity.getUsername() + "\"}";
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
