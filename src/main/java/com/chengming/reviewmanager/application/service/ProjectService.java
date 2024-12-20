package com.chengming.reviewmanager.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengming.reviewmanager.domain.project.entity.Project;
import com.chengming.reviewmanager.domain.project.repository.ProjectRepository;
import com.chengming.reviewmanager.infrastructure.exceptions.BusinessException;
import com.chengming.reviewmanager.infrastructure.toolkit.PageBuilder;
import com.chengming.reviewmanager.infrastructure.toolkit.RAssert;
import com.chengming.reviewmanager.interfaces.assembler.ProjectAssembler;
import com.chengming.reviewmanager.interfaces.dto.command.ProjectCmd;
import com.chengming.reviewmanager.interfaces.dto.query.QProject;
import com.chengming.reviewmanager.interfaces.dto.vo.ProjectVO;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ProjectService {
    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    @Resource
    private ProjectRepository projectRepository;

    @Resource
    private ReviewService reviewService;

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

    @Resource
    private DataSourceTransactionManager transactionManager;

    @Transactional(rollbackFor = Exception.class)
    public void batchInsert() {
        List<Project> projectList = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            Project project = new Project();
            project.setProjectNo(UUID.randomUUID().toString());
            projectList.add(project);
        }
        // 主线程事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus mainTransaction = transactionManager.getTransaction(def);


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<List<Project>> partition = Lists.partition(projectList, 100);
        CountDownLatch latch = new CountDownLatch(partition.size());
        AtomicBoolean flag = new AtomicBoolean(true);
//        for (List<Project> projects : partition) {
//            executorService.execute(()->{
//                extracted(projects, latch, transactionStatus);
//            });
//        }
//        Project project = new Project();
//        project.setProjectName("主线程插入的");
//        project.setProjectNo(UUID.randomUUID().toString());
//        projectRepository.insert(project);
//        transactionManager.commit(transactionStatus);
    }

    public void extracted(List<Project> projects, CountDownLatch latch) {
        TransactionStatus subTransaction = null;
        try {
            // 子线程事务定义 (每个子线程独立事务)
            DefaultTransactionDefinition subTxDef = new DefaultTransactionDefinition();
            subTxDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            subTransaction = transactionManager.getTransaction(subTxDef);
            for (Project project : projects) {
                project.setProjectName("子线程id：" + Thread.currentThread().getId());
                projectRepository.insert(project);
                throw new BusinessException("子线程异常");
            }
        } catch (Exception e) {

        } finally {
            latch.countDown();
        }
    }

    @Resource
    private TransactionMQProducer transactionMQProducer;

    @Transactional(rollbackFor = Exception.class)
    public void multiTransException() {
        Project project = new Project();
        project.setProjectName("multiTransException");
        projectRepository.insert(project);
        try {
            reviewService.insertReview();
        } catch (Exception e) {
            log.info("子事务异常exception, ", e);
        }
        log.info("是否能走到");
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg =
                        new Message("TopicTest1234", tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                TransactionSendResult sendResult = transactionMQProducer.sendMessageInTransaction(msg, null);

                System.out.println("sendResult:"+sendResult.getSendStatus()+", localStatus:"+ sendResult.getLocalTransactionState());
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
