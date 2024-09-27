package com.chengming.reviewmanager.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengming.reviewmanager.domain.project.entity.Project;
import com.chengming.reviewmanager.domain.project.repository.ProjectRepository;
import com.chengming.reviewmanager.infrastructure.toolkit.PageBuilder;
import com.chengming.reviewmanager.infrastructure.toolkit.RAssert;
import com.chengming.reviewmanager.interfaces.assembler.ProjectAssembler;
import com.chengming.reviewmanager.interfaces.dto.command.ProjectCmd;
import com.chengming.reviewmanager.interfaces.dto.query.QProject;
import com.chengming.reviewmanager.interfaces.dto.vo.ProjectVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Resource
    private ProjectRepository projectRepository;

    public Page<ProjectVO> list(QProject qProject) {
        Project project = ProjectAssembler.qProject2Project(qProject);
        Page<Project> projectPage = projectRepository.list(project, PageBuilder.buildQ(qProject.getPageNum(),
                qProject.getPageSize()));
        List<Project> records = projectPage.getRecords();
        return PageBuilder.buildR(projectPage, records.stream().map(ProjectAssembler::project2ProjectVO).toList());
    }

    public void update(ProjectCmd cmd) {
        Project project = ProjectAssembler.projectCmd2Project(cmd);
        boolean update = projectRepository.update(project);
        RAssert.isTrue(update, "project update failed");
    }

    public Long create(ProjectCmd cmd) {
        Project project = ProjectAssembler.projectCmd2Project(cmd);
        boolean insert = projectRepository.insert(project);
        RAssert.isTrue(insert, "project create failed");
        return project.getId();
    }

    public void delete(ProjectCmd cmd) {
        Project project = ProjectAssembler.projectCmd2Project(cmd);
        boolean delete = projectRepository.deleteById(project);
        RAssert.isTrue(delete, "project delete failed");
    }
}
