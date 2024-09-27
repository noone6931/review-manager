package com.chengming.reviewmanager.domain.user.repository;

import com.chengming.reviewmanager.domain.user.entity.User;
import com.chengming.reviewmanager.domain.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Resource
    private UserMapper userMapper;

    public boolean insert(User user) {
        int insert = userMapper.insert(user);
        return insert == 1;
    }

}
