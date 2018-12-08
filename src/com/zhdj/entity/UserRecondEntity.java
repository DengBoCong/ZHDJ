package com.zhdj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-24 10:34
 **/
@Entity
@Table(name = "user_recond", schema = "zhdj", catalog = "")
public class UserRecondEntity {
    private String userid;
    private int recondFlag;
    private String recondTime;
    private String recondContent;
    private String username;

    @Basic
    @Column(name = "userid", nullable = true, length = 50)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Id
    @Column(name = "recond_flag", nullable = false)
    public int getRecondFlag() {
        return recondFlag;
    }

    public void setRecondFlag(int recondFlag) {
        this.recondFlag = recondFlag;
    }

    @Basic
    @Column(name = "recond_time", nullable = true, length = 50)
    public String getRecondTime() {
        return recondTime;
    }

    public void setRecondTime(String recondTime) {
        this.recondTime = recondTime;
    }

    @Basic
    @Column(name = "recond_content", nullable = true, length = 1000)
    public String getRecondContent() {
        return recondContent;
    }

    public void setRecondContent(String recondContent) {
        this.recondContent = recondContent;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecondEntity that = (UserRecondEntity) o;
        return recondFlag == that.recondFlag &&
                Objects.equals(userid, that.userid) &&
                Objects.equals(recondTime, that.recondTime) &&
                Objects.equals(recondContent, that.recondContent) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userid, recondFlag, recondTime, recondContent, username);
    }
}
