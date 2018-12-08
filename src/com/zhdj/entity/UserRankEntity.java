package com.zhdj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-27 21:52
 **/
@Entity
@Table(name = "user_rank", schema = "zhdj", catalog = "")
public class UserRankEntity {
    private String userid;
    private Integer rankSource;
    private String sourceStituationfirst;
    private Integer rankSourceold;
    private String username;

    @Id
    @Column(name = "userid", nullable = false, length = 50)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "rank_source", nullable = true)
    public Integer getRankSource() {
        return rankSource;
    }

    public void setRankSource(Integer rankSource) {
        this.rankSource = rankSource;
    }

    @Basic
    @Column(name = "source_stituationfirst", nullable = true, length = 200)
    public String getSourceStituationfirst() {
        return sourceStituationfirst;
    }

    public void setSourceStituationfirst(String sourceStituationfirst) {
        this.sourceStituationfirst = sourceStituationfirst;
    }

    @Basic
    @Column(name = "rank_sourceold", nullable = true)
    public Integer getRankSourceold() {
        return rankSourceold;
    }

    public void setRankSourceold(Integer rankSourceold) {
        this.rankSourceold = rankSourceold;
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
        UserRankEntity that = (UserRankEntity) o;
        return Objects.equals(userid, that.userid) &&
                Objects.equals(rankSource, that.rankSource) &&
                Objects.equals(sourceStituationfirst, that.sourceStituationfirst) &&
                Objects.equals(rankSourceold, that.rankSourceold) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userid, rankSource, sourceStituationfirst, rankSourceold, username);
    }
}
