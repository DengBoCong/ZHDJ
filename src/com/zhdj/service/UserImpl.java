package com.zhdj.service;

import com.zhdj.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Iterator;
import java.util.List;

/**
 * @program: ZHDJData
 * @description:
 * @author: DBC
 * @create: 2018-07-27 16:10
 **/
public class UserImpl implements User {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(UserEntity user) {
        Session session = sessionFactory.openSession();
        Transaction tx= session.beginTransaction();
        UserEntity userEntity = null;
        userEntity = (UserEntity)session.get(UserEntity.class, user.getId());
        if(userEntity != null){
            return false;
        }
        session.save(user);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String UserId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = null;
        userEntity = (UserEntity)session.get(UserEntity.class, UserId);
        if(userEntity == null){
            return false;
        }
        session.delete(userEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String UserId, int flag, String s){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = null;
        userEntity = (UserEntity)session.get(UserEntity.class, UserId);
        if(userEntity == null){
            return false;
        }
        switch (flag){
            case 1: userEntity.setUsername(s);break;
            case 2: userEntity.setPassword(s);break;
            case 3: userEntity.setStatus(s);break;
            case 4: userEntity.setSector(s);break;
            case 5: userEntity.setSex(s);break;
            case 6: userEntity.setInstitute(s);break;
            case 7: userEntity.setGrade(s);break;
            case 8: userEntity.setBirthday(s);break;
            case 9: userEntity.setNation(s);break;
            case 10: userEntity.setPartytime(s);break;
            case 11: userEntity.setQq(s);break;
            case 12: userEntity.setPhone(s);break;
            case 13: userEntity.setEmail(s);break;
            case 14: userEntity.setIdNumber(s);break;
            case 15: userEntity.setImage(s);break;
            case 16: userEntity.setPartUser(s);break;
            case 17: userEntity.setTrainUser(s);break;
            case 18: userEntity.setId(s);break;
        }
        session.update(userEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updageRankFlag(String UserId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = null;
        userEntity = (UserEntity)session.get(UserEntity.class, UserId);
        if(userEntity == null){
            return false;
        }
        userEntity.setRankFlag(flag);
        session.update(userEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String getUser(String UserId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = null;
        userEntity = (UserEntity)session.get(UserEntity.class, UserId);
        if(userEntity == null){
            return "";
        }
        switch (flag){
            case 1: return userEntity.getId();
            case 2: return userEntity.getUsername();
            case 3: return userEntity.getPassword();
            case 4: return userEntity.getStatus();
            case 5: return userEntity.getSector();
            case 6: return userEntity.getSex();
            case 7: return userEntity.getInstitute();
            case 8: return userEntity.getGrade();
            case 9: return userEntity.getBirthday();
            case 10: return userEntity.getNation();
            case 11: return userEntity.getPartytime();
            case 12: return userEntity.getQq();
            case 13: return userEntity.getPhone();
            case 14: return userEntity.getEmail();
            case 15: return userEntity.getIdNumber();
            case 16: return userEntity.getImage();
            case 17: return userEntity.getPartUser();
            case 18: return userEntity.getTrainUser();
        }
        return "s";
    }

    @Override
    public int getRankFlag(String UserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = null;
        userEntity = (UserEntity)session.get(UserEntity.class, UserId);
        if(userEntity == null){
            return 0;
        }
        return userEntity.getRankFlag();
    }


    @Override
    public String list(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List users = session.createQuery("FROM UserEntity ").list();
        for(Iterator iterator = users.iterator(); iterator.hasNext();){
            String temp = "";
            UserEntity userEntity = (UserEntity)iterator.next();
            temp = temp + "{\"id\":\"" + userEntity.getId() + "\"";
            temp = temp + ",\"username\":\"" + userEntity.getUsername() + "\"";
            temp = temp + ",\"password\":\"" + userEntity.getPassword() + "\"";
            temp = temp + ",\"image\":\"" + userEntity.getImage() + "\"";
            temp = temp + ",\"rankFlag\":\"" + userEntity.getRankFlag() + "\"";
            temp = temp + ",\"status\":\"" + userEntity.getStatus() + "\"";
            temp = temp + ",\"sector\":\"" + userEntity.getSector() + "\"";
            temp = temp + ",\"sex\":\"" + userEntity.getSex() + "\"";
            temp = temp + ",\"institute\":\"" + userEntity.getInstitute() + "\"";
            temp = temp + ",\"grade\":\"" + userEntity.getGrade() + "\"";
            temp = temp + ",\"birthday\":\"" + userEntity.getBirthday() + "\"";
            temp = temp + ",\"nation\":\"" + userEntity.getNation() + "\"";
            temp = temp + ",\"partytime\":\"" + userEntity.getPartytime() + "\"";
            temp = temp + ",\"qq\":\"" + userEntity.getQq() + "\"";
            temp = temp + ",\"phone\":\"" + userEntity.getPhone() + "\"";
            temp = temp + ",\"email\":\"" + userEntity.getEmail() + "\"";
            temp = temp + ",\"part_user\":\"" + userEntity.getPartUser() + "\"";
            temp = temp + ",\"train_user\":\"" + userEntity.getTrainUser() + "\"";
            temp = temp + ",\"idNumber\":\"" + userEntity.getIdNumber() + "\"}";
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
    public String listMember() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List users = session.createQuery("FROM UserEntity ").list();
        for(Iterator iterator = users.iterator(); iterator.hasNext();){
            String temp = "";
            UserEntity userEntity = (UserEntity)iterator.next();
            temp = temp + "{\"id\":\"" + userEntity.getId() + "\"";
            temp = temp + ",\"username\":\"" + userEntity.getUsername() + "\"";
            temp = temp + ",\"password\":\"" + userEntity.getPassword() + "\"";
            temp = temp + ",\"image\":\"" + userEntity.getImage() + "\"";
            temp = temp + ",\"rankFlag\":\"" + userEntity.getRankFlag() + "\"";
            temp = temp + ",\"status\":\"" + userEntity.getStatus() + "\"";
            temp = temp + ",\"sector\":\"" + userEntity.getSector() + "\"";
            temp = temp + ",\"sex\":\"" + userEntity.getSex() + "\"";
            temp = temp + ",\"institute\":\"" + userEntity.getInstitute() + "\"";
            temp = temp + ",\"grade\":\"" + userEntity.getGrade() + "\"";
            temp = temp + ",\"birthday\":\"" + userEntity.getBirthday() + "\"";
            temp = temp + ",\"nation\":\"" + userEntity.getNation() + "\"";
            temp = temp + ",\"partytime\":\"" + userEntity.getPartytime() + "\"";
            temp = temp + ",\"qq\":\"" + userEntity.getQq() + "\"";
            temp = temp + ",\"phone\":\"" + userEntity.getPhone() + "\"";
            temp = temp + ",\"email\":\"" + userEntity.getEmail() + "\"";
            temp = temp + ",\"part_user\":\"" + userEntity.getPartUser() + "\"";
            temp = temp + ",\"train_user\":\"" + userEntity.getTrainUser() + "\"";
            temp = temp + ",\"idNumber\":\"" + userEntity.getIdNumber() + "\"}";
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
    public String listSector(String Sector) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List users = session.createQuery("FROM UserEntity ").list();
        for(Iterator iterator = users.iterator(); iterator.hasNext();){
            String temp = "";
            UserEntity userEntity = (UserEntity)iterator.next();
            if(userEntity.getRankFlag() == 1) continue;
            if(userEntity.getRankFlag() == 2) continue;
            if(!userEntity.getSector().equals(Sector)) continue;
            temp = temp + "{\"id\":\"" + userEntity.getId() + "\"";
            temp = temp + ",\"username\":\"" + userEntity.getUsername() + "\"";
            temp = temp + ",\"password\":\"" + userEntity.getPassword() + "\"";
            temp = temp + ",\"image\":\"" + userEntity.getImage() + "\"";
            temp = temp + ",\"rankFlag\":\"" + userEntity.getRankFlag() + "\"";
            temp = temp + ",\"status\":\"" + userEntity.getStatus() + "\"";
            temp = temp + ",\"sector\":\"" + userEntity.getSector() + "\"";
            temp = temp + ",\"sex\":\"" + userEntity.getSex() + "\"";
            temp = temp + ",\"institute\":\"" + userEntity.getInstitute() + "\"";
            temp = temp + ",\"grade\":\"" + userEntity.getGrade() + "\"";
            temp = temp + ",\"birthday\":\"" + userEntity.getBirthday() + "\"";
            temp = temp + ",\"nation\":\"" + userEntity.getNation() + "\"";
            temp = temp + ",\"partytime\":\"" + userEntity.getPartytime() + "\"";
            temp = temp + ",\"qq\":\"" + userEntity.getQq() + "\"";
            temp = temp + ",\"phone\":\"" + userEntity.getPhone() + "\"";
            temp = temp + ",\"email\":\"" + userEntity.getEmail() + "\"";
            temp = temp + ",\"part_user\":\"" + userEntity.getPartUser() + "\"";
            temp = temp + ",\"train_user\":\"" + userEntity.getTrainUser() + "\"";
            temp = temp + ",\"idNumber\":\"" + userEntity.getIdNumber() + "\"}";
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
    public String listItem(String UserId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = null;
        userEntity = (UserEntity) session.get(UserEntity.class, UserId);
        String temp = "";
        temp = temp + "{\"id\":\"" + userEntity.getId() + "\"";
        temp = temp + ",\"username\":\"" + userEntity.getUsername() + "\"";
        temp = temp + ",\"password\":\"" + userEntity.getPassword() + "\"";
        temp = temp + ",\"image\":\"" + userEntity.getImage() + "\"";
        temp = temp + ",\"rankFlag\":\"" + userEntity.getRankFlag() + "\"";
        temp = temp + ",\"status\":\"" + userEntity.getStatus() + "\"";
        temp = temp + ",\"sector\":\"" + userEntity.getSector() + "\"";
        temp = temp + ",\"sex\":\"" + userEntity.getSex() + "\"";
        temp = temp + ",\"institute\":\"" + userEntity.getInstitute() + "\"";
        temp = temp + ",\"grade\":\"" + userEntity.getGrade() + "\"";
        temp = temp + ",\"birthday\":\"" + userEntity.getBirthday() + "\"";
        temp = temp + ",\"nation\":\"" + userEntity.getNation() + "\"";
        temp = temp + ",\"partytime\":\"" + userEntity.getPartytime() + "\"";
        temp = temp + ",\"qq\":\"" + userEntity.getQq() + "\"";
        temp = temp + ",\"phone\":\"" + userEntity.getPhone() + "\"";
        temp = temp + ",\"email\":\"" + userEntity.getEmail() + "\"";
        temp = temp + ",\"part_user\":\"" + userEntity.getPartUser() + "\"";
        temp = temp + ",\"train_user\":\"" + userEntity.getTrainUser() + "\"";
        temp = temp + ",\"idNumber\":\"" + userEntity.getIdNumber() + "\"}";
        transaction.commit();
        session.close();
        return temp;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
