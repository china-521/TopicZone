package com.project.zone.bean;

import java.util.Date;

/**
 * @ClassName UserDetail
 * @Description 用户详情信息
 * @Author wk
 * @Date 2022/3/21 17:21
 * @Version 1.0
 */
public class UserDetail {
    private Integer id;
    private String realName;
    private String sex;
    private String IDNumber;
    private String phone;
    private String email;
    private Date birth;
    private String star;

    public UserDetail() {

    }

    public UserDetail(Integer id){
        this.id = id;
    }

    public UserDetail(Integer id, String realName, String sex, String IDNumber, String phone, String email, Date birth, String star) {
        this.id = id;
        this.realName = realName;
        this.sex = sex;
        this.IDNumber = IDNumber;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
        this.star = star;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", IDNumber='" + IDNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", star='" + star + '\'' +
                '}';
    }
}


