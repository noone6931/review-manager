package com.chengming.reviewmanager.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengming.reviewmanager.domain.review.entity.CodeReview;
import com.chengming.reviewmanager.domain.review.entity.Review;
import com.chengming.reviewmanager.domain.review.repository.CodeReviewRepository;
import com.chengming.reviewmanager.domain.review.repository.ReviewRepository;
import com.chengming.reviewmanager.domain.user.entity.User;
import com.chengming.reviewmanager.domain.user.repository.UserRepository;
import com.chengming.reviewmanager.infrastructure.toolkit.PageBuilder;
import com.chengming.reviewmanager.infrastructure.toolkit.RAssert;
import com.chengming.reviewmanager.interfaces.assembler.ReviewAssembler;
import com.chengming.reviewmanager.interfaces.assembler.UserAssembler;
import com.chengming.reviewmanager.interfaces.dto.command.CodeReviewCmd;
import com.chengming.reviewmanager.interfaces.dto.query.QReview;
import com.chengming.reviewmanager.interfaces.dto.vo.CodeReviewDetailVO;
import com.chengming.reviewmanager.interfaces.dto.vo.CodeReviewVO;
import com.chengming.reviewmanager.interfaces.dto.vo.ReviewVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    @Resource
    private ReviewRepository reviewRepository;
    @Resource
    private CodeReviewRepository codeReviewRepository;
    @Resource
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long create(CodeReviewCmd cmd) {
        User user = UserAssembler.codeReviewCmd2User(cmd);
        boolean insertUserResult = userRepository.insert(user);
        RAssert.isTrue(insertUserResult, "user insert failed");
        Review review = ReviewAssembler.codeReviewCmd2Review(cmd);
        review.setHoldUserId(user.getId());
        boolean insertReviewResult = reviewRepository.insert(review);
        RAssert.isTrue(insertReviewResult, "review insert failed");
        CodeReview codeReview = ReviewAssembler.codeReviewCmd2CodeReview(cmd);
        codeReview.setReviewId(review.getId());
        boolean insertCodeReviewResult = codeReviewRepository.insert(codeReview);
        RAssert.isTrue(insertCodeReviewResult, "codeReview insert failed");
        return review.getId();
    }

    public Page<ReviewVO> list(QReview qReview) {
        Page<Review> page = reviewRepository.list(qReview, PageBuilder.buildQ(qReview.getPageNum(),
                qReview.getPageSize()));
        return PageBuilder.buildR(page, page.getRecords().stream().map(ReviewAssembler::review2ReviewVO).toList());
    }

    public CodeReviewDetailVO detail(Long id) {
        Review review = reviewRepository.findById(id);
        RAssert.notNull(review, "review not found");
        CodeReview codeReview = codeReviewRepository.findByReviewId(id);
        RAssert.notNull(codeReview, "codeReview not found");

        CodeReviewDetailVO codeReviewDetailVO = new CodeReviewDetailVO();
        CodeReviewVO codeReviewVO = new CodeReviewVO();
        codeReviewDetailVO.setCodeReview(codeReviewVO);
        return codeReviewDetailVO;

    }
}
