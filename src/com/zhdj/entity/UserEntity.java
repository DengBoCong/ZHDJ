package com.zhdj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: ZHDJ
 * @description:
 * @author: DBC
 * @create: 2018-08-25 11:38
 **/
@Entity
@Table(name = "user", schema = "zhdj", catalog = "")
public class UserEntity {
    private String username;
    private String id;
    private String password;
    private String image;
    private int rankFlag;
    private String status;
    private String sector;
    private String sex;
    private String institute;
    private String grade;
    private String birthday;
    private String nation;
    private String partytime;
    private String qq;
    private String phone;
    private String email;
    private String idNumber;
    private String partUser;
    private String trainUser;

    @Basic
    @Column(name = "username", nullable = true, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "id", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 1000)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "rank_flag", nullable = false)
    public int getRankFlag() {
        return rankFlag;
    }

    public void setRankFlag(int rankFlag) {
        this.rankFlag = rankFlag;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "sector", nullable = false, length = 20)
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 10)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "institute", nullable = true, length = 20)
    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Basic
    @Column(name = "grade", nullable = true, length = 20)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "birthday", nullable = true, length = 20)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "nation", nullable = true, length = 10)
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Basic
    @Column(name = "partytime", nullable = true, length = 20)
    public String getPartytime() {
        return partytime;
    }

    public void setPartytime(String partytime) {
        this.partytime = partytime;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 20)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 20)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "id_number", nullable = true, length = 50)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "part_user", nullable = true, length = 20)
    public String getPartUser() {
        return partUser;
    }

    public void setPartUser(String partUser) {
        this.partUser = partUser;
    }

    @Basic
    @Column(name = "train_user", nullable = true, length = 20)
    public String getTrainUser() {
        return trainUser;
    }

    public void setTrainUser(String trainUser) {
        this.trainUser = trainUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return rankFlag == that.rankFlag &&
                Objects.equals(username, that.username) &&
                Objects.equals(id, that.id) &&
                Objects.equals(password, that.password) &&
                Objects.equals(image, that.image) &&
                Objects.equals(status, that.status) &&
                Objects.equals(sector, that.sector) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(institute, that.institute) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(nation, that.nation) &&
                Objects.equals(partytime, that.partytime) &&
                Objects.equals(qq, that.qq) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(idNumber, that.idNumber) &&
                Objects.equals(partUser, that.partUser) &&
                Objects.equals(trainUser, that.trainUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, id, password, image, rankFlag, status, sector, sex, institute, grade, birthday, nation, partytime, qq, phone, email, idNumber, partUser, trainUser);
    }
}
