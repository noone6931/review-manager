package com.chengming.reviewmanager.interfaces.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengming.reviewmanager.application.service.ProjectService;
import com.chengming.reviewmanager.domain.project.entity.Project;
import com.chengming.reviewmanager.interfaces.dto.command.ProjectCmd;
import com.chengming.reviewmanager.interfaces.dto.query.QProject;
import com.chengming.reviewmanager.interfaces.dto.vo.ProjectVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "项目管理")
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @Operation(summary = "项目管理列表")
    @PostMapping("/list")
    public Page<ProjectVO> list(@RequestBody QProject qProject) {
        return projectService.list(qProject);
    }
    @Operation(summary = "创建项目")
    @PostMapping("/create")
    public Long create(@RequestBody @Validated ProjectCmd cmd) {
        return projectService.create(cmd);
    }

    @Operation(summary = "修改项目")
    @PostMapping("/modify")
    public void modify(@RequestBody @Validated(ProjectCmd.ModifyProjectGroup.class) ProjectCmd cmd) {
        projectService.update(cmd);
    }

    @Operation(summary = "删除项目")
    @PostMapping("/delete")
    public void delete(@RequestBody @Validated(ProjectCmd.ModifyProjectGroup.class) ProjectCmd cmd) {
        projectService.delete(cmd);
    }

    @PostMapping("/multiTransException")
    public void multiTransException(){
        projectService.multiTransException();
    }

    public static void main(String[] args) {
        Project project;
        List<Project> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            project = new Project();
            project.setProjectNo(i+"");
            list.add(project);
        }
        List<String> collect = list.stream().map(Project::getProjectNo).toList();
        System.out.println(collect);
    }


    @PostMapping("/testExport")
    public void testExport() throws FileNotFoundException {
        QProject qProject = new QProject();
        qProject.setPageNum(1);
        qProject.setPageSize(100);
        Page<ProjectVO> list = this.list(qProject);

        File testExport = new File("testExport6.xlsx");
        // 创建 FileOutputStream（会创建文件）
        FileOutputStream fos = new FileOutputStream(testExport);

        System.out.println("11111");
//        EasyExcel.write(testExport, ProjectVO.class).sheet().doWrite(list.getRecords());
    }
}
