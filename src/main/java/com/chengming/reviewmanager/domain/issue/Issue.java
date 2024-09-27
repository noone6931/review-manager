package com.chengming.reviewmanager.domain.issue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Issue {
    private Long id;
    private Long reviewId;
    private Long assignUserId;
    private Long recordUserId;
    private Integer status;
    private String description;
    private Date handledTime;
    private Date dueTime;
    private String remark;
    private String suggestion;
    private Date createTime;
    private Date updateTime;
}
