package com.chengming.reviewmanager.infrastructure.exceptions;


import com.chengming.reviewmanager.infrastructure.enums.ResultEnum;

public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(String message) {
        super(message);
        this.code = ResultEnum.FAILED.getCode();
    }

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
