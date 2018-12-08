package com.zhdj.service;

import com.zhdj.entity.PhotoEntity;
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
 * @create: 2018-08-14 16:27
 **/
public class PhotoImpl implements Photo {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(PhotoEntity photoEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity1 = null;
        photoEntity1 = (PhotoEntity)session.get(PhotoEntity.class, photoEntity.getPhotoId());
        if(photoEntity1 != null){
            return false;
        }
        session.save(photoEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(int PhotoId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);
        if(photoEntity == null){
            return false;
        }
        session.delete(photoEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(int PhotoId, int flag, String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);
        if(photoEntity == null){
            return false;
        }
        switch (flag){
            case 1: photoEntity.setPhotoAuthor(s);break;
            case 2: photoEntity.setPhotoCover(s);break;
            case 3: photoEntity.setPhotoTime(s);break;
            case 4: photoEntity.setPhotoOwner(s);break;
            case 5: photoEntity.setAuthorCover(s);break;
        }
        session.update(photoEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateId(int PhotoId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);
        if(photoEntity == null){
            return false;
        }
        photoEntity.setPhotoId(num);
        session.update(photoEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateGood(int PhotoId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);
        if(photoEntity == null){
            return false;
        }
        photoEntity.setPhotoGood(num);
        session.update(photoEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateRead(int PhotoId, int num) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);
        if(photoEntity == null){
            return false;
        }

        photoEntity.setPhotoRead(num);
        session.saveOrUpdate(photoEntity);
        System.out.println(photoEntity.getPhotoRead());
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String getPhoto(int PhotoId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);
        if(photoEntity == null){
            return "f";
        }
        switch (flag){
            case 1: return photoEntity.getPhotoAuthor();
            case 2: return photoEntity.getPhotoCover();
            case 3: return photoEntity.getPhotoTime();
            case 4: return photoEntity.getPhotoOwner();
            case 5: return photoEntity.getAuthorCover();
        }
        return "f";
    }

    @Override
    public int getPhotoGood(int PhotoId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);

        if(photoEntity == null){
            return 0;
        }
        return photoEntity.getPhotoGood();
    }

    @Override
    public int getPhotoRead(int PhotoId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);
        if(photoEntity == null){
            return 0;
        }
        return photoEntity.getPhotoRead();
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM PhotoEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            PhotoEntity photoEntity = (PhotoEntity)iterator.next();
            temp = temp + "{\"id\":\"" + photoEntity.getPhotoId() + "\"";
            temp = temp + ",\"author\":\"" + photoEntity.getPhotoAuthor() + "\"";
            temp = temp + ",\"cover\":\"" + photoEntity.getPhotoCover() + "\"";
            temp = temp + ",\"date\":\"" + photoEntity.getPhotoTime() + "\"";
            temp = temp + ",\"good\":\"" + photoEntity.getPhotoGood() + "\"";
            temp = temp + ",\"read\":\"" + photoEntity.getPhotoRead() + "\"";
            temp = temp + ",\"author_cover\":\"" + photoEntity.getAuthorCover() + "\"";
            temp = temp + ",\"owner\":\"" + photoEntity.getPhotoOwner() + "\"}";
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
    public String listItem(int PhotoId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoEntity photoEntity = null;
        photoEntity = (PhotoEntity)session.get(PhotoEntity.class, PhotoId);
        String temp = "";
        temp = temp + "{\"id\":\"" + photoEntity.getPhotoId() + "\"";
        temp = temp + ",\"author\":\"" + photoEntity.getPhotoAuthor() + "\"";
        temp = temp + ",\"cover\":\"" + photoEntity.getPhotoCover() + "\"";
        temp = temp + ",\"date\":\"" + photoEntity.getPhotoTime() + "\"";
        temp = temp + ",\"good\":\"" + photoEntity.getPhotoGood() + "\"";
        temp = temp + ",\"read\":\"" + photoEntity.getPhotoRead() + "\"";
        temp = temp + ",\"author_cover\":\"" + photoEntity.getAuthorCover() + "\"";
        temp = temp + ",\"owner\":\"" + photoEntity.getPhotoOwner() + "\"}";
        transaction.commit();
        session.close();
        return temp;
    }

    @Override
    public String listKind(String owner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM PhotoEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            PhotoEntity photoEntity = (PhotoEntity)iterator.next();
            if(!photoEntity.getPhotoOwner().equals(owner)) continue;
            temp = temp + "{\"id\":\"" + photoEntity.getPhotoId() + "\"";
            temp = temp + ",\"author\":\"" + photoEntity.getPhotoAuthor() + "\"";
            temp = temp + ",\"cover\":\"" + photoEntity.getPhotoCover() + "\"";
            temp = temp + ",\"date\":\"" + photoEntity.getPhotoTime() + "\"";
            temp = temp + ",\"good\":\"" + photoEntity.getPhotoGood() + "\"";
            temp = temp + ",\"read\":\"" + photoEntity.getPhotoRead() + "\"";
            temp = temp + ",\"author_cover\":\"" + photoEntity.getAuthorCover() + "\"";
            temp = temp + ",\"owner\":\"" + photoEntity.getPhotoOwner() + "\"}";
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
        List photoList = session.createQuery("FROM PhotoEntity r order by r.photoId desc ").list();

        if(photoList == null){
            return 0;
        }
        PhotoEntity photoEntity = (PhotoEntity)photoList.get(0);
        return photoEntity.getPhotoId();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
