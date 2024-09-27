package com.chengming.reviewmanager.interfaces.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengming.reviewmanager.application.service.ReviewService;
import com.chengming.reviewmanager.interfaces.dto.command.CodeReviewCmd;
import com.chengming.reviewmanager.interfaces.dto.query.QReview;
import com.chengming.reviewmanager.interfaces.dto.vo.CodeReviewDetailVO;
import com.chengming.reviewmanager.interfaces.dto.vo.ReviewVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "评审管理")
@RestController
@RequestMapping("/codeReview")
public class ReviewController {
    @Resource
    private ReviewService reviewService;

    @Operation(summary = "评审列表")
    @PostMapping("/list")
    public Page<ReviewVO> list(@RequestBody QReview qReview) {
        return reviewService.list(qReview);
    }

    @Operation(summary = "代码评审详情")
    @PostMapping("/detail")
    public CodeReviewDetailVO detail(@RequestParam("id") Long id) {
        return reviewService.detail(id);
    }

    @PostMapping("/create")
    @Operation(summary = "创建代码评审")
    public Long create(@Validated(CodeReviewCmd.CreateCodeReviewGroup.class)
                       @RequestBody CodeReviewCmd cmd) {
        return reviewService.create(cmd);
    }

    @PostMapping("/modify")
    @Operation(summary = "修改代码评审")
    public void modify(@Validated(CodeReviewCmd.ModifyCodeReviewGroup.class)
                       @RequestBody CodeReviewCmd modifyCodeReviewCmd) {
    }


    @PostMapping("/delete")
    @Operation(summary = "删除代码评审")
    public void delete(@Validated(CodeReviewCmd.ModifyCodeReviewGroup.class)
                       @RequestBody CodeReviewCmd modifyCodeReviewCmd) {
    }


}
