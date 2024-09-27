package com.chengming.reviewmanager.domain.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@TableName("t_projects")
public class Project {
    @TableId(type=IdType.AUTO)
    private Long id;
    private String projectName;
    private String projectNo;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
