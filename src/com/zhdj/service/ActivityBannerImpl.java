package com.zhdj.service;

import com.zhdj.entity.ActivitybannerEntity;
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
 * @create: 2018-08-03 09:38
 **/
public class ActivityBannerImpl implements ActivityBanner{
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(ActivitybannerEntity activitybannerEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activity = null;
        activity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, activitybannerEntity.getId());
        if(activity != null){
            return false;
        }
        session.save(activitybannerEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activityEntity = null;
        activityEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);
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
        ActivitybannerEntity activitybannerEntity = null;
        activitybannerEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);
        if(activitybannerEntity == null){
            return false;
        }
        switch (flag){
            case 1: activitybannerEntity.setAuthorName(s);break;
            case 2: activitybannerEntity.setTitle(s);break;
            case 3: activitybannerEntity.setContent(s);break;
            case 4: activitybannerEntity.setCover(s);break;
            case 5: activitybannerEntity.setPublishedAt(s);break;
            case 6: activitybannerEntity.setSummary(s);break;
            case 7: activitybannerEntity.setType(s);break;
            case 8: activitybannerEntity.setPartDeadline(s);break;
            case 9: activitybannerEntity.setSubmitDeadline(s);break;
        }
        session.update(activitybannerEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateFlag(int ActivityId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activitybannerEntity = null;
        activitybannerEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);
        if(activitybannerEntity == null) return false;
        activitybannerEntity.setFlag(num);
        session.save(activitybannerEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateSubmitFlag(int ActivityId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activitybannerEntity = null;
        activitybannerEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);
        if(activitybannerEntity == null) return false;
        activitybannerEntity.setSubmitFlag(num);
        session.save(activitybannerEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updatePostId(int ActivityId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activityEntity = null;
        activityEntity = (ActivitybannerEntity) session.get(ActivitybannerEntity.class, ActivityId);
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
    public boolean updatePartNum(int ActivityId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activityEntity = null;
        activityEntity = (ActivitybannerEntity) session.get(ActivitybannerEntity.class, ActivityId);
        if(activityEntity == null){
            return false;
        }
        activityEntity.setPartNum(num);
        session.save(activityEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public int getPostId(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activityEntity = null;
        activityEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);

        if(activityEntity == null){
            return 0;
        }
        return activityEntity.getPostId();
    }

    @Override
    public int getPartNum(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activityEntity = null;
        activityEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);

        if(activityEntity == null){
            return 0;
        }
        return activityEntity.getPartNum();
    }

    @Override
    public int getFlag(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activitybannerEntity = null;
        activitybannerEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);

        if(activitybannerEntity == null) return -1;
        return activitybannerEntity.getFlag();
    }

    @Override
    public int getSubmitFlag(int ActivityId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activitybannerEntity = null;
        activitybannerEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);

        if(activitybannerEntity == null) return -1;
        return activitybannerEntity.getSubmitFlag();
    }

    @Override
    public String getActivity(int ActivityId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ActivitybannerEntity activityEntity = null;
        activityEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);

        if(activityEntity == null){
            return "s";
        }
        switch (flag){
            case 1: return activityEntity.getContent();
            case 2: return activityEntity.getTitle();
            case 3: return activityEntity.getAuthorName();
            case 4: return activityEntity.getCover();
            case 5: return activityEntity.getPublishedAt();
            case 6: return activityEntity.getType();
            case 7: return activityEntity.getSummary();
            case 8: return activityEntity.getPartDeadline();
            case 9: return activityEntity.getSubmitDeadline();
        }
        return "s";
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM ActivitybannerEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            ActivitybannerEntity activityEntity = (ActivitybannerEntity)iterator.next();
            temp = temp + "{\"id\":\"" + activityEntity.getId() + "\"";
            temp = temp + ",\"title\":\"" + activityEntity.getTitle() + "\"";
            temp = temp + ",\"author_name\":\"" + activityEntity.getAuthorName() + "\"";
            temp = temp + ",\"cover\":\"" + activityEntity.getCover() + "\"";
            temp = temp + ",\"published_at\":\"" + activityEntity.getPublishedAt() + "\"";
            temp = temp + ",\"summary\":\"" + activityEntity.getSummary() + "\"";
            temp = temp + ",\"type\":\"" + activityEntity.getType() + "\"";
            temp = temp + ",\"post_id\":\"" + activityEntity.getPostId() + "\"";
            temp = temp + ",\"flag\":\"" + activityEntity.getFlag() + "\"";
            temp = temp + ",\"part_num\":\"" + activityEntity.getPartNum() + "\"";
            temp = temp + ",\"part_deadline\":\"" + activityEntity.getPartDeadline() + "\"";
            temp = temp + ",\"submit_deadline\":\"" + activityEntity.getSubmitDeadline() + "\"";
            temp = temp + ",\"submit_flag\":\"" + activityEntity.getSubmitFlag() + "\"";
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
    public String listActivity() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM ActivitybannerEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            ActivitybannerEntity activityEntity = (ActivitybannerEntity)iterator.next();
            if(activityEntity.getType().equals("activity")){
                temp = temp + "{\"id\":\"" + activityEntity.getId() + "\"";
                temp = temp + ",\"title\":\"" + activityEntity.getTitle() + "\"";
                temp = temp + ",\"author_name\":\"" + activityEntity.getAuthorName() + "\"";
                temp = temp + ",\"cover\":\"" + activityEntity.getCover() + "\"";
                temp = temp + ",\"published_at\":\"" + activityEntity.getPublishedAt() + "\"";
                temp = temp + ",\"summary\":\"" + activityEntity.getSummary() + "\"";
                temp = temp + ",\"type\":\"" + activityEntity.getType() + "\"";
                temp = temp + ",\"post_id\":\"" + activityEntity.getPostId() + "\"";
                temp = temp + ",\"flag\":\"" + activityEntity.getFlag() + "\"";
                temp = temp + ",\"part_deadline\":\"" + activityEntity.getPartDeadline() + "\"";
                temp = temp + ",\"submit_deadline\":\"" + activityEntity.getSubmitDeadline() + "\"";
                temp = temp + ",\"submit_flag\":\"" + activityEntity.getSubmitFlag() + "\"";
                temp = temp + ",\"part_num\":\"" + activityEntity.getPartNum() + "\"";
                temp = temp + ",\"content\":\"" + activityEntity.getContent() + "\"}";
                if(count == 0) temp = temp + "]";
                else temp = temp + ",";
                count++;
            }
            listJson = temp + listJson;
        }
        listJson = "[" + listJson;
        transaction.commit();
        session.close();
        return listJson;
    }

    @Override
    public String listMessage() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM ActivitybannerEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            ActivitybannerEntity activityEntity = (ActivitybannerEntity)iterator.next();
            if(activityEntity.getType().equals("message")){
                temp = temp + "{\"id\":\"" + activityEntity.getId() + "\"";
                temp = temp + ",\"title\":\"" + activityEntity.getTitle() + "\"";
                temp = temp + ",\"author_name\":\"" + activityEntity.getAuthorName() + "\"";
                temp = temp + ",\"cover\":\"" + activityEntity.getCover() + "\"";
                temp = temp + ",\"published_at\":\"" + activityEntity.getPublishedAt() + "\"";
                temp = temp + ",\"summary\":\"" + activityEntity.getSummary() + "\"";
                temp = temp + ",\"type\":\"" + activityEntity.getType() + "\"";
                temp = temp + ",\"post_id\":\"" + activityEntity.getPostId() + "\"";
                temp = temp + ",\"flag\":\"" + activityEntity.getFlag() + "\"";
                temp = temp + ",\"part_num\":\"" + activityEntity.getPartNum() + "\"";
                temp = temp + ",\"part_deadline\":\"" + activityEntity.getPartDeadline() + "\"";
                temp = temp + ",\"submit_flag\":\"" + activityEntity.getSubmitFlag() + "\"";
                temp = temp + ",\"submit_deadline\":\"" + activityEntity.getSubmitDeadline() + "\"";
                temp = temp + ",\"content\":\"" + activityEntity.getContent() + "\"}";
                if(count == 0) temp = temp + "]";
                else temp = temp + ",";
                count++;
            }
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
        ActivitybannerEntity activityEntity = null;
        activityEntity = (ActivitybannerEntity)session.get(ActivitybannerEntity.class, ActivityId);
        String temp = "";
        temp = temp + "{\"id\":\"" + activityEntity.getId() + "\"";
        temp = temp + ",\"title\":\"" + activityEntity.getTitle() + "\"";
        temp = temp + ",\"author_name\":\"" + activityEntity.getAuthorName() + "\"";
        temp = temp + ",\"cover\":\"" + activityEntity.getCover() + "\"";
        temp = temp + ",\"published_at\":\"" + activityEntity.getPublishedAt() + "\"";
        temp = temp + ",\"summary\":\"" + activityEntity.getSummary() + "\"";
        temp = temp + ",\"type\":\"" + activityEntity.getType() + "\"";
        temp = temp + ",\"post_id\":\"" + activityEntity.getPostId() + "\"";
        temp = temp + ",\"flag\":\"" + activityEntity.getFlag() + "\"";
        temp = temp + ",\"part_num\":\"" + activityEntity.getPartNum() + "\"";
        temp = temp + ",\"part_deadline\":\"" + activityEntity.getPartDeadline() + "\"";
        temp = temp + ",\"submit_flag\":\"" + activityEntity.getSubmitFlag() + "\"";
        temp = temp + ",\"submit_deadline\":\"" + activityEntity.getSubmitDeadline() + "\"";
        temp = temp + ",\"content\":\"" + activityEntity.getContent() + "\"}";
        transaction.commit();
        session.close();
        return temp;
    }

    @Override
    public int getMaxId() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List photoList = session.createQuery("FROM ActivitybannerEntity r order by r.id desc ").list();
        session.close();
        if(photoList == null){
            return 0;
        }
        ActivitybannerEntity activitybannerEntity = (ActivitybannerEntity)photoList.get(0);
        return activitybannerEntity.getId();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
