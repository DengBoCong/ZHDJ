package com.zhdj.service;

import com.zhdj.entity.FileEntity;

public interface FileSubmit {
    public boolean add(FileEntity fileEntity);
    public boolean delete(String FileUserId);
    public boolean update(String FileUserId, int flag, String s);
    public boolean updateFileFlag(String FileUserId, int num);
    public String getFile(String FileUserId, int flag);
    public int getFileFlag(String FileUserId);
    public String list();
    public String listItem(String FileUserId);
    public int itemCount(int FileFlag);
}
