package com.chengming.reviewmanager.domain.review.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengming.reviewmanager.domain.review.entity.CodeReview;
import com.chengming.reviewmanager.domain.review.mapper.CodeReviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class CodeReviewRepository {
    @Resource
    private CodeReviewMapper codeReviewMapper;
    public boolean insert(CodeReview codeReview) {
        return codeReviewMapper.insert(codeReview) == 1;
    }

    public CodeReview findById(Long id) {
        return codeReviewMapper.selectById(id);
    }

    public CodeReview findByReviewId(Long reviewId) {
        LambdaQueryWrapper<CodeReview> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeReview::getReviewId, reviewId);
        return codeReviewMapper.selectOne(queryWrapper);
    }
}
