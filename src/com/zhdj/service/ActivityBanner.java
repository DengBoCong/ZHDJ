package com.zhdj.service;

import com.zhdj.entity.ActivitybannerEntity;

public interface ActivityBanner {
    public boolean add(ActivitybannerEntity activitybannerEntity);
    public boolean delete(int ActivityId);
    public boolean update(int ActivityId, int flag, String s);
    public boolean updatePostId(int ActivityId, int num);
    public boolean updatePartNum(int ActivityId, int num);
    public boolean updateFlag(int ActivityId, int num);
    public boolean updateSubmitFlag(int ActivityId, int num);
    public int getPostId(int ActivityId);
    public int getFlag(int ActivityId);
    public int getSubmitFlag(int ActivityId);
    public int getPartNum(int ActivityId);
    public String getActivity(int ActivityId, int flag);
    public String list();
    public String listItem(int ActivityId);
    public String listActivity();
    public String listMessage();
    public int getMaxId();
}
