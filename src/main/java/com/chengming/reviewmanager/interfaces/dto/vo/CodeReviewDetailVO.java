package com.chengming.reviewmanager.interfaces.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CodeReviewDetailVO {
    @Schema(description = "代码评审详情")
    private CodeReviewVO codeReview;
    @Schema(description = "评审问题列表")
    private List<IssueVO> issues;
}
