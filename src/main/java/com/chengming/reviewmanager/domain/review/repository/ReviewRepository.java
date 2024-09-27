package com.chengming.reviewmanager.domain.review.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengming.reviewmanager.domain.project.entity.Project;
import com.chengming.reviewmanager.domain.review.entity.Review;
import com.chengming.reviewmanager.domain.review.mapper.ReviewMapper;
import com.chengming.reviewmanager.interfaces.dto.query.QReview;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {

    @Resource
    private ReviewMapper reviewMapper;

    public boolean insert(Review review) {
        return reviewMapper.insert(review) == 1;
    }

    public Page<Review> list(QReview qReview, Page<Review> page) {
        LambdaQueryWrapper<Review> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .ge(qReview.getPlanTimeFrom() != null, Review::getPlanTime, qReview.getPlanTimeFrom())
                .le(qReview.getPlanTimeTo() != null, Review::getPlanTime, qReview.getPlanTimeTo())
                .ge(qReview.getStartTimeFrom() != null, Review::getStartTime, qReview.getStartTimeFrom())
                .le(qReview.getStartTimeTo() != null, Review::getStartTime, qReview.getStartTimeTo())
                .eq(qReview.getReviewType() != null, Review::getReviewType, qReview.getReviewType())
                .eq(qReview.getStatus() != null, Review::getStatus, qReview.getStatus())
                .likeRight(qReview.getTitle() != null, Review::getTitle, qReview.getTitle());
        return reviewMapper.selectPage(page, queryWrapper);
    }


    public Review findById(Long id) {
        return reviewMapper.selectById(id);
    }


}
