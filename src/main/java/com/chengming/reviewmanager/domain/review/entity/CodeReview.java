package com.chengming.reviewmanager.domain.review.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CodeReview {
    private Long id;
    private Long reviewId;
    private Integer codeReviewType;
    private String serviceName;
    private String serviceUnitName;
    private Date createTime;
    private Date updateTime;
}
