package com.zhdj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-15 19:47
 **/
@Entity
@Table(name = "photo", schema = "zhdj", catalog = "")
public class PhotoEntity {
    private int photoId;
    private String photoAuthor;
    private String photoTime;
    private String photoCover;
    private Integer photoGood;
    private Integer photoRead;
    private String photoOwner;
    private String authorCover;

    @Id
    @Column(name = "photo_id", nullable = false)
    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "photo_author", nullable = true, length = 50)
    public String getPhotoAuthor() {
        return photoAuthor;
    }

    public void setPhotoAuthor(String photoAuthor) {
        this.photoAuthor = photoAuthor;
    }

    @Basic
    @Column(name = "photo_time", nullable = true, length = 50)
    public String getPhotoTime() {
        return photoTime;
    }

    public void setPhotoTime(String photoTime) {
        this.photoTime = photoTime;
    }

    @Basic
    @Column(name = "photo_cover", nullable = true, length = 1000)
    public String getPhotoCover() {
        return photoCover;
    }

    public void setPhotoCover(String photoCover) {
        this.photoCover = photoCover;
    }

    @Basic
    @Column(name = "photo_good", nullable = true)
    public Integer getPhotoGood() {
        return photoGood;
    }

    public void setPhotoGood(Integer photoGood) {
        this.photoGood = photoGood;
    }

    @Basic
    @Column(name = "photo_read", nullable = true)
    public Integer getPhotoRead() {
        return photoRead;
    }

    public void setPhotoRead(Integer photoRead) {
        this.photoRead = photoRead;
    }

    @Basic
    @Column(name = "photo_owner", nullable = true, length = 100)
    public String getPhotoOwner() {
        return photoOwner;
    }

    public void setPhotoOwner(String photoOwner) {
        this.photoOwner = photoOwner;
    }

    @Basic
    @Column(name = "author_cover", nullable = true, length = 1000)
    public String getAuthorCover() {
        return authorCover;
    }

    public void setAuthorCover(String authorCover) {
        this.authorCover = authorCover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoEntity that = (PhotoEntity) o;
        return photoId == that.photoId &&
                Objects.equals(photoAuthor, that.photoAuthor) &&
                Objects.equals(photoTime, that.photoTime) &&
                Objects.equals(photoCover, that.photoCover) &&
                Objects.equals(photoGood, that.photoGood) &&
                Objects.equals(photoRead, that.photoRead) &&
                Objects.equals(photoOwner, that.photoOwner) &&
                Objects.equals(authorCover, that.authorCover);
    }

    @Override
    public int hashCode() {

        return Objects.hash(photoId, photoAuthor, photoTime, photoCover, photoGood, photoRead, photoOwner, authorCover);
    }
}
