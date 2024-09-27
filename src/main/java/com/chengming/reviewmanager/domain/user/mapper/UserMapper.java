package com.chengming.reviewmanager.domain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengming.reviewmanager.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
