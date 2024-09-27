package com.chengming.reviewmanager.domain.review.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengming.reviewmanager.domain.review.entity.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper extends BaseMapper<Review> {
}
