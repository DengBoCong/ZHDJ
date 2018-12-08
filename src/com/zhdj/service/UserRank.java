package com.zhdj.service;

import com.zhdj.entity.UserRankEntity;

public interface UserRank {
    public boolean add(UserRankEntity userRankEntity);
    public boolean delete(String UserId);
    public boolean update(String UserId, int flag, String s);
    public boolean updageRankSource(String UserId, int flag);
    public boolean updageRankSourceOld(String UserId, int flag);
    public String getUserRank(String UserId, int num);
    public int getRankSource(String UserId);
    public int getRankSourceOld(String UserId);
    public String list();
}
