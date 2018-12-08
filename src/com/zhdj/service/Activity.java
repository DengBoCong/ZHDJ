package com.zhdj.service;

import com.zhdj.entity.ActivityEntity;

public interface Activity {
    public boolean add(ActivityEntity activity);
    public boolean delete(int ActivityId);
    public boolean update(int ActivityId, int flag, String s);
    public boolean updatePostId(int ActivityId, int num);
    public int getPostId(int ActivityId);
    public String getActivity(int ActivityId, int flag);
    public String list();
    public String listItem(int ActivityId);
}
