package com.zhdj.service;

import com.zhdj.entity.ActivitypartEntity;
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
 * @create: 2018-08-17 08:37
 **/
public class ActivityPartImpl implements ActivityPart {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(ActivitypartEntity activitypartEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activity = null;
        activity = (ActivitypartEntity)session.get(ActivitypartEntity.class, activitypartEntity.getPartFlag());
        if(activity != null){
            return false;
        }
        session.save(activitypartEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String ActivityPartId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activitypartEntity = null;
        activitypartEntity = (ActivitypartEntity)session.get(ActivitypartEntity.class, ActivityPartId);
        if(activitypartEntity == null){
            return false;
        }
        session.delete(activitypartEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String ActivityPartId, int flag, String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activitypartEntity = null;
        activitypartEntity = (ActivitypartEntity)session.get(ActivitypartEntity.class, ActivityPartId);
        if(activitypartEntity == null){
            return false;
        }
        switch (flag){
            case 1: activitypartEntity.setPartFileurl(s);break;
            case 2: activitypartEntity.setPartFlag(s);break;
            case 3: activitypartEntity.setPartUsername(s);break;
            case 4: activitypartEntity.setPartSubmitTime(s);break;
            case 5: activitypartEntity.setPartTime(s);break;
        }
        session.update(activitypartEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateId(String ActivityPartId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activitypartEntity = null;
        activitypartEntity = (ActivitypartEntity)session.get(ActivitypartEntity.class, ActivityPartId);
        if(activitypartEntity == null) return false;
        activitypartEntity.setPartId(num);
        session.save(activitypartEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateSituation(String ActivityPartId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activitypartEntity = null;
        activitypartEntity = (ActivitypartEntity)session.get(ActivitypartEntity.class, ActivityPartId);
        if(activitypartEntity == null) return false;
        activitypartEntity.setPartSituation(num);
        session.save(activitypartEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public int getId(String ActivityPartId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activitypartEntity = null;
        activitypartEntity = (ActivitypartEntity)session.get(ActivitypartEntity.class, ActivityPartId);
        if(activitypartEntity == null){
            return 0;
        }
        return activitypartEntity.getPartId();
    }

    @Override
    public int getSituation(String ActivityPartId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activitypartEntity = null;
        activitypartEntity = (ActivitypartEntity)session.get(ActivitypartEntity.class, ActivityPartId);
        if(activitypartEntity == null){
            return 0;
        }
        return activitypartEntity.getPartSituation();
    }

    @Override
    public String getActivityPart(String ActivityPartId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activitypartEntity = null;
        activitypartEntity = (ActivitypartEntity)session.get(ActivitypartEntity.class, ActivityPartId);
        if(activitypartEntity == null){
            return "s";
        }
        switch (flag){
            case 1: return activitypartEntity.getPartFileurl();
            case 2: return activitypartEntity.getPartFlag();
            case 3: return activitypartEntity.getPartSubmitTime();
            case 4: return activitypartEntity.getPartTime();
            case 5: return activitypartEntity.getPartUsername();
        }
        return "s";
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM ActivitypartEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            ActivitypartEntity activitypartEntity = (ActivitypartEntity)iterator.next();
            temp = temp + "{\"part_id\":\"" + activitypartEntity.getPartId() + "\"";
            temp = temp + ",\"part_username\":\"" + activitypartEntity.getPartUsername() + "\"";
            temp = temp + ",\"part_situation\":\"" + activitypartEntity.getPartSituation() + "\"";
            temp = temp + ",\"part_fileurl\":\"" + activitypartEntity.getPartFileurl() + "\"";
            temp = temp + ",\"part_flag\":\"" + activitypartEntity.getPartFlag() + "\"";
            temp = temp + ",\"part_submit_time\":\"" + activitypartEntity.getPartSubmitTime() + "\"";
            temp = temp + ",\"part_time\":\"" + activitypartEntity.getPartTime() + "\"}";
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
    public String listItem(String ActivityPartId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitypartEntity activitypartEntity = null;
        activitypartEntity = (ActivitypartEntity)session.get(ActivitypartEntity.class, ActivityPartId);
        String temp = "";
        temp = temp + "{\"part_id\":\"" + activitypartEntity.getPartId() + "\"";
        temp = temp + ",\"part_username\":\"" + activitypartEntity.getPartUsername() + "\"";
        temp = temp + ",\"part_situation\":\"" + activitypartEntity.getPartSituation() + "\"";
        temp = temp + ",\"part_fileurl\":\"" + activitypartEntity.getPartFileurl() + "\"";
        temp = temp + ",\"part_flag\":\"" + activitypartEntity.getPartFlag() + "\"";
        temp = temp + ",\"part_submit_time\":\"" + activitypartEntity.getPartSubmitTime() + "\"";
        temp = temp + ",\"part_time\":\"" + activitypartEntity.getPartTime() + "\"}";
        transaction.commit();
        session.close();
        return temp;
    }

    @Override
    public String listForOne(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM ActivitypartEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            ActivitypartEntity activitypartEntity = (ActivitypartEntity)iterator.next();
            if(activitypartEntity.getPartId() != ActivityId) continue;
            temp = temp + "{\"part_id\":\"" + activitypartEntity.getPartId() + "\"";
            temp = temp + ",\"part_username\":\"" + activitypartEntity.getPartUsername() + "\"";
            temp = temp + ",\"part_situation\":\"" + activitypartEntity.getPartSituation() + "\"";
            temp = temp + ",\"part_fileurl\":\"" + activitypartEntity.getPartFileurl() + "\"";
            temp = temp + ",\"part_flag\":\"" + activitypartEntity.getPartFlag() + "\"";
            temp = temp + ",\"part_submit_time\":\"" + activitypartEntity.getPartSubmitTime() + "\"";
            temp = temp + ",\"part_time\":\"" + activitypartEntity.getPartTime() + "\"}";
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
