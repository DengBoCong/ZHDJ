package com.zhdj.service;

import com.zhdj.entity.MessageEntity;

public interface Message {
    public boolean add(MessageEntity messageEntity);
    public boolean delete(int MessageId);
    public boolean update(int MessageId, int flag, String s);
    public boolean updatePostId(int MessageId, int num);
    public int getPostId(int MessageId);
    public String getActivity(int MessageId, int flag);
    public String list();
    public String listItem(int MessageId);
}
