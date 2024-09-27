package com.chengming.reviewmanager.interfaces.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class IssueCmd {
    @NotNull(message = "评审id不能为空")
    private Long codeReviewId;

    @NotEmpty(message = "问题不能为空")
    private List<IssueCmdItem> issues;

    @Getter
    @Setter
    @ToString
    public static class IssueCmdItem {
        @NotNull(message = "问题id不能为空", groups = ModifyIssueGroup.class)
        private Long id;
        @Schema(description = "跟进人名称")
        @NotBlank(message = "跟进人名称不能为空")
        private String assignUserName;
        @Schema(description = "记录人名称")
        @NotBlank(message = "记录人名称不能为空")
        private String recordUserName;
        @Schema(description = "问题描述")
        @NotBlank(message = "问题描述不能为空")
        private String description;
        @Schema(description = "问题处理意见")
        private String suggestion;
        @Schema(description = "截至时间")
        @NotNull(message = "截至时间不能为空")
        private Date dueTime;
        @Schema(description = "问题处理时间")
        private Date handledTime;
        @Schema(description = "问题状态，1: 未处理，2: 已处理")
        @NotNull(message = "问题状态不能为空")
        private Integer status;
        @Schema(description = "备注")
        private String remark;
    }

    public interface ModifyIssueGroup {
    }
}
