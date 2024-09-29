package com.chengming.reviewmanager.domain.project.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengming.reviewmanager.domain.project.entity.Project;
import com.chengming.reviewmanager.domain.project.mapper.ProjectMapper;
import com.chengming.reviewmanager.infrastructure.exceptions.BusinessException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class ProjectRepository1 {

    private static final Logger log = LoggerFactory.getLogger(ProjectRepository1.class);
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

    @Transactional(rollbackFor = Exception.class)
    public void extracted1(List<Project> projects, CountDownLatch latch) {
        for (Project project : projects) {
            project.setProjectName("子线程id："+  Thread.currentThread().getId());
            log.info("子线程：{}, latch:{} ", Thread.currentThread().getId(), latch.getCount());
            if (latch.getCount() % 6 == 0) {
                log.info("子线程：{}", Thread.currentThread().getId());
                throw new BusinessException("子线程异常");
            }
            insert(project);
        }
    }
}
