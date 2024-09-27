package com.chengming.reviewmanager.interfaces.controller;

import com.chengming.reviewmanager.interfaces.dto.command.IssueCmd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "问题管理")
@RestController
@RequestMapping("/issue")
public class IssueController {
    @Operation(summary = "添加问题")
    @PostMapping("/add")
    public void add(@RequestBody IssueCmd cmd) {

    }

    @Operation(summary = "修改问题")
    @PostMapping("/update")
    public void update(@Validated(IssueCmd.ModifyIssueGroup.class)
                       @RequestBody IssueCmd cmd) {
    }

    @Operation(summary = "删除问题")
    @PostMapping("/delete")
    public void delete(@Validated(IssueCmd.ModifyIssueGroup.class)
                       @RequestBody IssueCmd cmd) {

    }

}
