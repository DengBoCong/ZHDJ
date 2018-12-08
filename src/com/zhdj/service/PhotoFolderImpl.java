
package com.zhdj.service;

import com.zhdj.entity.PhotoFolderEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sound.midi.Track;
import java.util.Iterator;
import java.util.List;


/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-12 10:40
 **/

public class PhotoFolderImpl implements PhotoFolder {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(PhotoFolderEntity photoFolderEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoFolderEntity photoFolder = null;
        photoFolder = (PhotoFolderEntity)session.get(PhotoFolderEntity.class, photoFolderEntity.getId());
        if(photoFolder != null){
            return false;
        }
        session.save(photoFolderEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String PhotoFolderId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoFolderEntity photoFolderEntity = null;
        photoFolderEntity = (PhotoFolderEntity)session.get(PhotoFolderEntity.class, PhotoFolderId);
        if(photoFolderEntity == null){
            return false;
        }
        session.delete(photoFolderEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String PhotoFolderId, int flag, String s) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoFolderEntity photoFolderEntity = null;
        photoFolderEntity = (PhotoFolderEntity)session.get(PhotoFolderEntity.class, PhotoFolderId);
        if(photoFolderEntity == null){
            return false;
        }
        switch (flag){
            case 1: photoFolderEntity.setId(s);break;
            case 2: photoFolderEntity.setAuthor(s);break;
            case 3: photoFolderEntity.setCover(s);break;
            case 4: photoFolderEntity.setDate(s);break;
            case 5: photoFolderEntity.setDescripe(s);break;
        }
        session.update(photoFolderEntity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String getPhotoFolder(String PhotoFolderId, int flag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoFolderEntity photoFolderEntity = null;
        photoFolderEntity = (PhotoFolderEntity)session.get(PhotoFolderEntity.class, PhotoFolderId);
        if(photoFolderEntity == null){
            return "f";
        }
        switch (flag){
            case 1: return photoFolderEntity.getId();
            case 2: return photoFolderEntity.getAuthor();
            case 3: return photoFolderEntity.getCover();
            case 4: return photoFolderEntity.getDate();
            case 5: return photoFolderEntity.getDescripe();
        }
        return "f";
    }

    @Override
    public String list() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String listJson = "";
        int count = 0;
        List activity = session.createQuery("FROM PhotoFolderEntity ").list();
        for(Iterator iterator = activity.iterator(); iterator.hasNext();){
            String temp = "";
            PhotoFolderEntity photoFolderEntity = (PhotoFolderEntity)iterator.next();
            temp = temp + "{\"id\":\"" + photoFolderEntity.getId() + "\"";
            temp = temp + ",\"author\":\"" + photoFolderEntity.getAuthor() + "\"";
            temp = temp + ",\"cover\":\"" + photoFolderEntity.getCover() + "\"";
            temp = temp + ",\"descripe\":\"" + photoFolderEntity.getDescripe() + "\"";
            temp = temp + ",\"date\":\"" + photoFolderEntity.getDate() + "\"}";
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
    public String listItem(String PhotoFolderId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PhotoFolderEntity photoFolderEntity = null;
        photoFolderEntity = (PhotoFolderEntity)session.get(PhotoFolderEntity.class, PhotoFolderId);
        String temp = "";
        temp = temp + "{\"id\":\"" + photoFolderEntity.getId() + "\"";
        temp = temp + ",\"author\":\"" + photoFolderEntity.getAuthor() + "\"";
        temp = temp + ",\"descripe\":\"" + photoFolderEntity.getDescripe() + "\"";
        temp = temp + ",\"cover\":\"" + photoFolderEntity.getCover() + "\"";
        temp = temp + ",\"date\":\"" + photoFolderEntity.getDate() + "\"}";
        transaction.commit();
        session.close();
        return temp;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
