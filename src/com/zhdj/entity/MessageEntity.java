package com.zhdj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-03 16:03
 **/
@Entity
@Table(name = "message", schema = "zhdj", catalog = "")
public class MessageEntity {
    private int id;
    private String title;
    private String authorName;
    private String cover;
    private String publishedAt;
    private String summary;
    private String content;
    private String type;
    private Integer postId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "author_name", nullable = true, length = 20)
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Basic
    @Column(name = "cover", nullable = true, length = 1000)
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "published_at", nullable = true, length = 100)
    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Basic
    @Column(name = "summary", nullable = true, length = 100)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 10000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "post_id", nullable = true)
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(authorName, that.authorName) &&
                Objects.equals(cover, that.cover) &&
                Objects.equals(publishedAt, that.publishedAt) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(content, that.content) &&
                Objects.equals(type, that.type) &&
                Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, authorName, cover, publishedAt, summary, content, type, postId);
    }
}
