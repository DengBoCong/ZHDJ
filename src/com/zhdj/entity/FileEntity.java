package com.zhdj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-27 12:26
 **/
@Entity
@Table(name = "file", schema = "zhdj", catalog = "")
public class FileEntity {
    private String fileUserid;
    private Integer fileFlag;
    private String fileUrl;
    private String fileSubmittime;
    private String fileUsername;
    private String fileName;
    private String fileId;

    @Basic
    @Column(name = "file_userid", nullable = true, length = 50)
    public String getFileUserid() {
        return fileUserid;
    }

    public void setFileUserid(String fileUserid) {
        this.fileUserid = fileUserid;
    }

    @Basic
    @Column(name = "file_flag", nullable = true)
    public Integer getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(Integer fileFlag) {
        this.fileFlag = fileFlag;
    }

    @Basic
    @Column(name = "file_url", nullable = true, length = 1000)
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Basic
    @Column(name = "file_submittime", nullable = true, length = 50)
    public String getFileSubmittime() {
        return fileSubmittime;
    }

    public void setFileSubmittime(String fileSubmittime) {
        this.fileSubmittime = fileSubmittime;
    }

    @Basic
    @Column(name = "file_username", nullable = true, length = 50)
    public String getFileUsername() {
        return fileUsername;
    }

    public void setFileUsername(String fileUsername) {
        this.fileUsername = fileUsername;
    }

    @Basic
    @Column(name = "file_name", nullable = true, length = 50)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Id
    @Column(name = "file_id", nullable = false, length = 100)
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return Objects.equals(fileUserid, that.fileUserid) &&
                Objects.equals(fileFlag, that.fileFlag) &&
                Objects.equals(fileUrl, that.fileUrl) &&
                Objects.equals(fileSubmittime, that.fileSubmittime) &&
                Objects.equals(fileUsername, that.fileUsername) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileId, that.fileId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fileUserid, fileFlag, fileUrl, fileSubmittime, fileUsername, fileName, fileId);
    }
}
