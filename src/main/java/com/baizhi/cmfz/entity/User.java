package com.baizhi.cmfz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * (CmfzUser)实体类
 *
 * @author makejava
 * @since 2019-01-03 16:00:37
 */
@TableName("cmfz_user")
public class User implements Serializable {
    private static final long serialVersionUID = 365975885846005269L;
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    
    private String telphone;
    
    private String password;
    @TableField("user_image")
    private String userImage;
    
    private String nickname;
    
    private String name;
    
    private String sex;
    
    private String autograph;
    @TableField("user_province")
    private String userProvince;
    @TableField("user_city")
    private String userCity;
    @TableField("guru_id")
    private Integer guruId;
    @TableField("user_status")
    private Integer userStatus;

    private Date createTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", telphone='" + telphone + '\'' +
                ", password='" + password + '\'' +
                ", userImage='" + userImage + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", autograph='" + autograph + '\'' +
                ", userProvince='" + userProvince + '\'' +
                ", userCity='" + userCity + '\'' +
                ", guruId=" + guruId +
                ", userStatus=" + userStatus +
                '}';
    }
}