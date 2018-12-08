package com.zhdj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-12 10:22
 **/
@Entity
@Table(name = "photo_folder", schema = "zhdj", catalog = "")
public class PhotoFolderEntity {
    private String id;
    private String descripe;
    private String date;
    private String author;
    private String cover;

    @Id
    @Column(name = "id", nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "descripe", nullable = true, length = 1000)
    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

    @Basic
    @Column(name = "date", nullable = true, length = 50)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 20)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "cover", nullable = true, length = 200)
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFolderEntity that = (PhotoFolderEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descripe, that.descripe) &&
                Objects.equals(date, that.date) &&
                Objects.equals(author, that.author) &&
                Objects.equals(cover, that.cover);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, descripe, date, author, cover);
    }
}
