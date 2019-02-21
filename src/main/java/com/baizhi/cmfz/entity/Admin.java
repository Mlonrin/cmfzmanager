package com.baizhi.cmfz.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (CmfzAdmin)实体类
 *
 * @author makejava
 * @since 2019-01-03 16:07:42
 */
@TableName("cmfz_admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = -51231573011872362L;
    
    private Integer id;
    
    private String username;
    
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}