package com.chengming.reviewmanager.interfaces.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectCmd {
    @NotNull(message = "id不能为空", groups = ModifyProjectGroup.class)
    private Long id;
    @NotBlank(message = "项目名不能为空")
    @Schema(description = "项目名称")
    private String projectName;
    @NotBlank(message = "项目编号不能为空")
    @Schema(description = "项目编号")
    private String projectNo;

    public interface ModifyProjectGroup {
    }
}
