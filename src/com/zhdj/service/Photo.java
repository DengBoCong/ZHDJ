package com.zhdj.service;

import com.zhdj.entity.PhotoEntity;

public interface Photo{
    public boolean add(PhotoEntity photoEntity);
    public boolean delete(int PhotoId);
    public boolean update(int PhotoId, int flag, String s);
    public boolean updateId(int PhotoId, int num);
    public boolean updateGood(int PhotoId, int num);
    public boolean updateRead(int PhotoId, int num);
    public String getPhoto(int PhotoId, int flag);
    public int getPhotoGood(int PhotoId);
    public int getPhotoRead(int PhotoId);
    public String list();
    public String listItem(int PhotoId);
    public String listKind(String owner);
    public int getMaxId();
}
