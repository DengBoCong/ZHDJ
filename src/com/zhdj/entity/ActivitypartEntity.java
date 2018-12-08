package com.zhdj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-17 08:46
 **/
@Entity
@Table(name = "activitypart", schema = "zhdj", catalog = "")
public class ActivitypartEntity {
    private String partUsername;
    private Integer partId;
    private Integer partSituation;
    private String partFileurl;
    private String partFlag;
    private String partSubmitTime;
    private String partTime;

    @Basic
    @Column(name = "part_username", nullable = true, length = 20)
    public String getPartUsername() {
        return partUsername;
    }

    public void setPartUsername(String partUsername) {
        this.partUsername = partUsername;
    }

    @Basic
    @Column(name = "part_id", nullable = true)
    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    @Basic
    @Column(name = "part_situation", nullable = true)
    public Integer getPartSituation() {
        return partSituation;
    }

    public void setPartSituation(Integer partSituation) {
        this.partSituation = partSituation;
    }

    @Basic
    @Column(name = "part_fileurl", nullable = true, length = 1000)
    public String getPartFileurl() {
        return partFileurl;
    }

    public void setPartFileurl(String partFileurl) {
        this.partFileurl = partFileurl;
    }

    @Id
    @Column(name = "part_flag", nullable = false, length = 50)
    public String getPartFlag() {
        return partFlag;
    }

    public void setPartFlag(String partFlag) {
        this.partFlag = partFlag;
    }

    @Basic
    @Column(name = "part_submit_time", nullable = true, length = 100)
    public String getPartSubmitTime() {
        return partSubmitTime;
    }

    public void setPartSubmitTime(String partSubmitTime) {
        this.partSubmitTime = partSubmitTime;
    }

    @Basic
    @Column(name = "part_time", nullable = true, length = 100)
    public String getPartTime() {
        return partTime;
    }

    public void setPartTime(String partTime) {
        this.partTime = partTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitypartEntity that = (ActivitypartEntity) o;
        return Objects.equals(partUsername, that.partUsername) &&
                Objects.equals(partId, that.partId) &&
                Objects.equals(partSituation, that.partSituation) &&
                Objects.equals(partFileurl, that.partFileurl) &&
                Objects.equals(partFlag, that.partFlag) &&
                Objects.equals(partSubmitTime, that.partSubmitTime) &&
                Objects.equals(partTime, that.partTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(partUsername, partId, partSituation, partFileurl, partFlag, partSubmitTime, partTime);
    }
}
