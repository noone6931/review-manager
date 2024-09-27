package com.chengming.reviewmanager.interfaces.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class IssueVO {
    @Schema(description = "跟进人名称")
    private String assignUserName;
    @Schema(description = "记录人名称")
    private String recordUserName;
    @Schema(description = "问题描述")
    private String description;
    @Schema(description = "问题处理意见")
    private String suggestion;
    @Schema(description = "截至时间")
    private Date dueTime;
    @Schema(description = "问题处理时间")
    private Date handledTime;
    @Schema(description = "问题状态，1: 未处理，2: 已处理")
    private Integer status;
    @Schema(description = "备注")
    private String remark;
}
