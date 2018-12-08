package com.zhdj.service;

import com.zhdj.entity.PhotoFolderEntity;

public interface PhotoFolder {
    public boolean add(PhotoFolderEntity photoFolderEntity);
    public boolean delete(String PhotoFolderId);
    public boolean update(String PhotoFolderId, int flag, String s);
    public String getPhotoFolder(String PhotoFolderId, int flag);
    public String list();
    public String listItem(String PhotoFolderId);
}
