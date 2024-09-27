package com.chengming.reviewmanager.interfaces.assembler;

import com.chengming.reviewmanager.domain.project.entity.Project;
import com.chengming.reviewmanager.infrastructure.enums.BaseStatusEnum;
import com.chengming.reviewmanager.interfaces.dto.command.ProjectCmd;
import com.chengming.reviewmanager.interfaces.dto.query.QProject;
import com.chengming.reviewmanager.interfaces.dto.vo.ProjectVO;

import java.util.Optional;

public class ProjectAssembler {

    public static Project projectCmd2Project(ProjectCmd dto) {
        Project project = new Project();
        Optional.ofNullable(dto.getId()).ifPresent(project::setId);
        project.setProjectName(dto.getProjectName());
        project.setProjectNo(dto.getProjectNo());
        project.setStatus(BaseStatusEnum.NORMAL.getCode());
        return project;
    }

    public static Project qProject2Project(QProject qProject) {
        Project project = new Project();
        project.setProjectName(qProject.getProjectName());
        project.setProjectNo(qProject.getProjectNo());
        project.setStatus(BaseStatusEnum.NORMAL.getCode());
        return project;
    }

    public static ProjectVO project2ProjectVO(Project project) {
        ProjectVO projectVO = new ProjectVO();
        projectVO.setProjectName(project.getProjectName());
        projectVO.setProjectNo(project.getProjectNo());
        projectVO.setId(project.getId());
        return projectVO;
    }
}
