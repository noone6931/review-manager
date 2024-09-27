package com.chengming.reviewmanager.interfaces.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class QReview extends QPage {
    @Schema(description = "评审标题")
    private String title;
    @Schema(description = "开始时间")
    private Date startTimeFrom;
    @Schema(description = "结束时间")
    private Date startTimeTo;
    @Schema(description = "计划开始时间")
    private Date planTimeFrom;
    @Schema(description = "计划结束时间")
    private Date planTimeTo;
    @Schema(description = "评审类型，1: 代码评审，2: 详设评审")
    private Integer reviewType;
    @Schema(description = "评审状态，1: 未开始， 2进行中，3已结束")
    private Integer status;
}
