package com.chengming.reviewmanager.domain.review.entity;


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
@TableName("t_reviews")
public class Review {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String title;
    private String location;
    private Date startTime;
    private Date planTime;
    private Integer status;
    private Integer reviewType;
    private Long holdUserId;
    private Date createTime;
    private Date updateTime;
}
