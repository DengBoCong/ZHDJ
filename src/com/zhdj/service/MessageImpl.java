package com.zhdj.service;

import com.zhdj.entity.MessageEntity;
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
 * @create: 2018-08-03 16:08
 **/
public class MessageImpl implements Message {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(MessageEntity messageEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageEntity message = null;
        message = (MessageEntity)session.get(MessageEntity.class, messageEntity.getId());
        if(message != null){
            return false;
        }
        session.save(messageEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(int MessageId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageEntity messageEntity = null;
        messageEntity = (MessageEntity)session.get(MessageEntity.class, MessageId);
        if(messageEntity == null){
            return false;
        }
        session.delete(messageEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(int MessageId, int flag, String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageEntity messageEntity = null;
        messageEntity = (MessageEntity)session.get(MessageEntity.class, MessageId);
        if(messageEntity == null){
            return false;
        }
        switch (flag){
            case 1: messageEntity.setAuthorName(s);break;
            case 2: messageEntity.setTitle(s);break;
            case 3: messageEntity.setContent(s);break;
            case 4: messageEntity.setCover(s);break;
            case 5: messageEntity.setPublishedAt(s);break;
            case 6: messageEntity.setSummary(s);break;
            case 7: messageEntity.setType(s);break;
        }
        session.update(messageEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updatePostId(int MessageId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageEntity messageEntity = null;
        messageEntity = (MessageEntity) session.get(MessageEntity.class, MessageId);
        if(messageEntity == null){
            return false;
        }
        messageEntity.setPostId(num);
        session.save(messageEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public int getPostId(int MessageId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageEntity messageEntity = null;
        messageEntity = (MessageEntity)session.get(MessageEntity.class, MessageId);
        if(messageEntity == null){
            return 0;
        }
        return messageEntity.getPostId();
    }

    @Override
    public String getActivity(int MessageId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageEntity messageEntity = null;
        messageEntity = (MessageEntity)session.get(MessageEntity.class, MessageId);
        if(messageEntity == null){
            return "";
        }
        switch (flag){
            case 1: return messageEntity.getContent();
            case 2: return messageEntity.getTitle();
            case 3: return messageEntity.getAuthorName();
            case 4: return messageEntity.getCover();
            case 5: return messageEntity.getPublishedAt();
            case 6: return messageEntity.getType();
            case 7: return messageEntity.getSummary();
        }
        return "";
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List message = session.createQuery("FROM MessageEntity ").list();
        for(Iterator iterator = message.iterator(); iterator.hasNext();){
            String temp = "";
            MessageEntity messageEntity = (MessageEntity)iterator.next();
            temp = temp + "{\"id\":\"" + messageEntity.getId() + "\"";
            temp = temp + ",\"title\":\"" + messageEntity.getTitle() + "\"";
            temp = temp + ",\"author_name\":\"" + messageEntity.getAuthorName() + "\"";
            temp = temp + ",\"cover\":\"" + messageEntity.getCover() + "\"";
            temp = temp + ",\"published_at\":\"" + messageEntity.getPublishedAt() + "\"";
            temp = temp + ",\"summary\":\"" + messageEntity.getSummary() + "\"";
            temp = temp + ",\"type\":\"" + messageEntity.getType() + "\"";
            temp = temp + ",\"post_id\":\"" + messageEntity.getPostId() + "\"";
            temp = temp + ",\"content\":\"" + messageEntity.getContent() + "\"}";
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
    public String listItem(int MessageId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageEntity messageEntity = null;
        messageEntity = (MessageEntity) session.get(MessageEntity.class, MessageId);
        String temp = "";
        temp = temp + "{\"id\":\"" + messageEntity.getId() + "\"";
        temp = temp + ",\"title\":\"" + messageEntity.getTitle() + "\"";
        temp = temp + ",\"author_name\":\"" + messageEntity.getAuthorName() + "\"";
        temp = temp + ",\"cover\":\"" + messageEntity.getCover() + "\"";
        temp = temp + ",\"published_at\":\"" + messageEntity.getPublishedAt() + "\"";
        temp = temp + ",\"summary\":\"" + messageEntity.getSummary() + "\"";
        temp = temp + ",\"type\":\"" + messageEntity.getType() + "\"";
        temp = temp + ",\"post_id\":\"" + messageEntity.getPostId() + "\"";
        temp = temp + ",\"content\":\"" + messageEntity.getContent() + "\"}";
        transaction.commit();
        session.close();
        return temp;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
