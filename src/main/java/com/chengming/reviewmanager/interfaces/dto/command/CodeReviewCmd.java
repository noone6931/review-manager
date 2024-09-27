package com.chengming.reviewmanager.interfaces.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CodeReviewCmd {
    @NotNull(message = "id不能为空", groups = ModifyCodeReviewGroup.class)
    private Long id;

    @NotNull(message = "项目id不能为空")
    @Schema(description = "项目id")
    private Long projectId;

    @NotBlank(message = "评审标题不能为空")
    @Schema(description = "评审标题")
    private String title;

    @Schema(description = "评审地点")
    private String location;

    @NotNull(message = "计划评审时间不能为空")
    @Schema(description = "计划评审时间")
    private Date planTime;

    @Schema(description = "评审时间")
    private Date startTime;

    @Schema(description = "评审主持人")
    private String holdUserName;

    @NotNull(message = "评审类型不能为空")
    @Schema(description = "评审类型")
    private Integer codeReviewType;

    @Schema(description = "服务名称")
    @NotBlank(message = "服务名称不能为空")
    private String serviceName;

    @Schema(description = "服务单位名称")
    private String serviceUnitName;

    public interface CreateCodeReviewGroup {
    }

    public interface ModifyCodeReviewGroup {
    }
}
