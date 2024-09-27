package com.chengming.reviewmanager.interfaces.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QProject extends QPage {
    @Schema(description = "项目名称")
    private String projectName;
    @Schema(description = "项目编号")
    private String projectNo;


}
