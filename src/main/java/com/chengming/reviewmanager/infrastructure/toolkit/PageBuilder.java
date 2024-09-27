package com.chengming.reviewmanager.infrastructure.toolkit;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class PageBuilder {
    public static <T> Page<T> buildQ(Integer pageNum, Integer pageSize) {
        return new Page<>(pageNum, pageSize);
    }


    public static <T, S> Page<T> buildR(Page<S> pageSource, List<T> result) {
        Page<T> pageResult = new Page<>();
        return pageResult.setTotal(pageSource.getTotal()).setRecords(result);
    }
}
