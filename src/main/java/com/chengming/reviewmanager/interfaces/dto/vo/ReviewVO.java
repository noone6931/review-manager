package com.chengming.reviewmanager.interfaces.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReviewVO {
    private Long id;
    @Schema(description = "评审标题")
    private String title;
    @Schema(description = "开始时间")
    private Date startTime;
    @Schema(description = "预计开始时间")
    private Date planTime;
    @Schema(description = "评审状态，1: 未开始， 2进行中，3已结束")
    private Integer status;
    @Schema(description = "评审类型，1: 代码评审，2: 详设评审")
    private Integer reviewType;
    @Schema(description = "项目名称")
    private String projectName;
    @Schema(description = "评审地点")
    private String location;
}
