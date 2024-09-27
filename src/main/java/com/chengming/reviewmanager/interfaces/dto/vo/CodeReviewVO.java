package com.chengming.reviewmanager.interfaces.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CodeReviewVO extends ReviewVO {
    @Schema(description = "服务名称")
    private String serviceName;
    @Schema(description = "服务单元名称")
    private String serviceUnitName;
    @Schema(description = "评审类型，1: 代码评审，2: 详设评审")
    private Integer codeReviewType;
}
