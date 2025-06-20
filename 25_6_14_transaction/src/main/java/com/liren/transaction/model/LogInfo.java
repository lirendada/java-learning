package com.liren.transaction.model;

import lombok.Data;

import java.util.Date;

@Data
public class LogInfo {
    private Integer id;
    private String userName;
    private String op;
    private Date createTime;
    private Date updateTime;
}
