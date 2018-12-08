package com.zhdj.service;

import com.zhdj.entity.UserRecondEntity;

public interface UserRecond {
    public boolean add(UserRecondEntity userRecondEntity);
    public boolean delete(String UserId);
    public boolean update(String UserId, int flag, String s);
    public boolean updageRecondFlag(String UserId, int flag);
    public String getUserRecond(String UserId, int num);
    public int getRecondFlag(String UserId);
    public String list();
    public String listUser(String UserId);
    public int getMaxId();
}
