package com.zhdj.service;

import com.zhdj.entity.ActivitypartEntity;

public interface ActivityPart {
    public boolean add(ActivitypartEntity activitypartEntity);
    public boolean delete(String ActivityPartId);
    public boolean update(String ActivityPartId, int flag, String s);
    public boolean updateId(String ActivityPartId, int num);
    public boolean updateSituation(String ActivityPartId, int num);
    public int getId(String ActivityPartId);
    public int getSituation(String ActivityPartId);
    public String getActivityPart(String ActivityPartId, int flag);
    public String list();
    public String listItem(String ActivityPartId);
    public String listForOne(int ActivityId);
}
