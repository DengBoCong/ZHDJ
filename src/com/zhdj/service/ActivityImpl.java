package com.zhdj.service;

import com.zhdj.entity.ActivityEntity;
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
 * @create: 2018-08-01 23:02
 **/
public class ActivityImpl implements Activity {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(ActivityEntity activity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivityEntity activityEntity = null;
        activityEntity = (ActivityEntity)session.get(ActivityEntity.class, activity.getId());
        if(activityEntity != null){
            return false;
        }
        session.save(activity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivityEntity activityEntity = null;
        activityEntity = (ActivityEntity)session.get(ActivityEntity.class, ActivityId);
        if(activityEntity == null){
            return false;
        }
        session.delete(activityEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(int ActivityId, int flag, String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivityEntity activityEntity = null;
        activityEntity = (ActivityEntity)session.get(ActivityEntity.class, ActivityId);
        if(activityEntity == null){
            return false;
        }
        switch (flag){
            case 1: activityEntity.setAuthorName(s);break;
            case 2: activityEntity.setTitle(s);break;
            case 3: activityEntity.setContent(s);break;
            case 4: activityEntity.setCover(s);break;
            case 5: activityEntity.setPublishedAt(s);break;
            case 6: activityEntity.setSummary(s);break;
            case 7: activityEntity.setType(s);break;
        }
        session.update(activityEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updatePostId(int ActivityId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivityEntity activityEntity = null;
        activityEntity = (ActivityEntity) session.get(ActivityEntity.class, ActivityId);
        if(activityEntity == null){
            return false;
        }
        activityEntity.setPostId(num);
        session.save(activityEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public int getPostId(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivityEntity activityEntity = null;
        activityEntity = (ActivityEntity)session.get(ActivityEntity.class, ActivityId);
        if(activityEntity == null){
            return 0;
        }
        return activityEntity.getPostId();
    }

    @Override
    public String getActivity(int ActivityId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivityEntity activityEntity = null;
        activityEntity = (ActivityEntity)session.get(ActivityEntity.class, ActivityId);
        if(activityEntity == null){
            return "";
        }
        switch (flag){
            case 1: return activityEntity.getContent();
            case 2: return activityEntity.getTitle();
            case 3: return activityEntity.getAuthorName();
            case 4: return activityEntity.getCover();
            case 5: return activityEntity.getPublishedAt();
            case 6: return activityEntity.getType();
            case 7: return activityEntity.getSummary();
        }
        return "";
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM ActivityEntity ").list();
        for(Iterator iterator = activity.iterator();iterator.hasNext();){
            String temp = "";
            ActivityEntity activityEntity = (ActivityEntity)iterator.next();
            temp = temp + "{\"id\":\"" + activityEntity.getId() + "\"";
            temp = temp + ",\"title\":\"" + activityEntity.getTitle() + "\"";
            temp = temp + ",\"author_name\":\"" + activityEntity.getAuthorName() + "\"";
            temp = temp + ",\"cover\":\"" + activityEntity.getCover() + "\"";
            temp = temp + ",\"published_at\":\"" + activityEntity.getPublishedAt() + "\"";
            temp = temp + ",\"summary\":\"" + activityEntity.getSummary() + "\"";
            temp = temp + ",\"type\":\"" + activityEntity.getType() + "\"";
            temp = temp + ",\"post_id\":\"" + activityEntity.getPostId() + "\"";
            temp = temp + ",\"content\":\"" + activityEntity.getContent() + "\"}";
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
    public String listItem(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivityEntity activityEntity = null;
        activityEntity = (ActivityEntity)session.get(ActivityEntity.class, ActivityId);
        String temp = "";
        temp = temp + "{\"id\":\"" + activityEntity.getId() + "\"";
        temp = temp + ",\"title\":\"" + activityEntity.getTitle() + "\"";
        temp = temp + ",\"author_name\":\"" + activityEntity.getAuthorName() + "\"";
        temp = temp + ",\"cover\":\"" + activityEntity.getCover() + "\"";
        temp = temp + ",\"published_at\":\"" + activityEntity.getPublishedAt() + "\"";
        temp = temp + ",\"summary\":\"" + activityEntity.getSummary() + "\"";
        temp = temp + ",\"type\":\"" + activityEntity.getType() + "\"";
        temp = temp + ",\"post_id\":\"" + activityEntity.getPostId() + "\"";
        temp = temp + ",\"content\":\"" + activityEntity.getContent() + "\"}";
        transaction.commit();
        session.close();
        return temp;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
