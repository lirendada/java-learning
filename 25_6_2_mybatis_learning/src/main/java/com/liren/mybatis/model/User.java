package com.liren.mybatis.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private String phone;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
