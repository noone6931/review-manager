package com.chengming.reviewmanager.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BaseStatusEnum {
    NORMAL(1, "正常"),
    DELETED(-1, "已删除");

    private final int code;
    private final String desc;

}
