package com.chengming.reviewmanager.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResultEnum{

    SUCCESS(2001,"接口调用成功"),
    FAILED(5001,"接口响应失败"),

    ;

    private final Integer code;
    private final String message;
}
