package com.chengming.reviewmanager.interfaces.assembler;

import com.chengming.reviewmanager.domain.review.entity.CodeReview;
import com.chengming.reviewmanager.domain.review.entity.Review;
import com.chengming.reviewmanager.interfaces.dto.command.CodeReviewCmd;
import com.chengming.reviewmanager.interfaces.dto.vo.ReviewVO;

public class ReviewAssembler {
    public static Review codeReviewCmd2Review(CodeReviewCmd cmd) {
        Review review = new Review();
        review.setTitle(cmd.getTitle());
        review.setReviewType(cmd.getCodeReviewType());
        review.setLocation(cmd.getLocation());
        review.setPlanTime(cmd.getPlanTime());
        review.setStartTime(cmd.getStartTime());
        return review;
    }

    public static CodeReview codeReviewCmd2CodeReview(CodeReviewCmd cmd) {
        CodeReview codeReview = new CodeReview();
        codeReview.setServiceName(cmd.getServiceName());
        codeReview.setServiceUnitName(cmd.getServiceUnitName());
        codeReview.setCodeReviewType(cmd.getCodeReviewType());
        return codeReview;
    }

    public static ReviewVO review2ReviewVO(Review review) {
        ReviewVO reviewVO = new ReviewVO();
        reviewVO.setId(review.getId());
        reviewVO.setLocation(review.getLocation());
        reviewVO.setPlanTime(review.getPlanTime());
        reviewVO.setReviewType(review.getReviewType());
        reviewVO.setStartTime(review.getStartTime());
        reviewVO.setStatus(review.getStatus());
        return reviewVO;
    }
}
