package com.zhdj.service;

import com.zhdj.entity.UserEntity;

public interface User {
    public boolean add(UserEntity userEntity);
    public boolean delete(String UserId);
    public boolean update(String UserId, int flag, String s);
    public boolean updageRankFlag(String UserId, int flag);
    public String getUser(String UserId, int num);
    public int getRankFlag(String UserId);
    public String list();
    public String listMember();
    public String listSector(String Sector);
    public String listItem(String UserId);
}
