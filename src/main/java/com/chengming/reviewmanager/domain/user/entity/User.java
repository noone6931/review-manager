package com.chengming.reviewmanager.domain.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String username;
    private Integer role;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
