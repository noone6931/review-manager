package com.chengming.reviewmanager.domain.project.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengming.reviewmanager.domain.project.entity.Project;
import com.chengming.reviewmanager.domain.project.mapper.ProjectMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    @Resource
    private ProjectMapper projectMapper;

    public boolean insert(Project project) {
        int insert = projectMapper.insert(project);
        return insert == 1;
    }

    public Project findById(Long id) {
        return projectMapper.selectById(id);
    }

    public boolean update(Project project) {
        int update = projectMapper.updateById(project);
        return update == 1;
    }

    public Page<Project> list(Project project, Page<Project> page) {
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        boolean projectNameNotBlank = StringUtils.isNotBlank(project.getProjectName());
        boolean projectNoNotBlank = StringUtils.isNotBlank(project.getProjectNo());
        queryWrapper.eq(Project::getStatus, project.getStatus())
                .likeRight(projectNameNotBlank, Project::getProjectName, project.getProjectName())
                .likeRight(projectNoNotBlank, Project::getProjectNo, project.getProjectNo());
        return projectMapper.selectPage(page, queryWrapper);
    }

    public boolean deleteById(Project project) {
        int delete = projectMapper.deleteById(project);
        return delete == 1;
    }
}
