package com.chengming.reviewmanager.interfaces.dto.vo;

import com.chengming.reviewmanager.infrastructure.enums.ResultEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Result<T> {
    private String message;
    private Integer code;
    private T data;

    public Result(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.SUCCESS.getMessage(), ResultEnum.SUCCESS.getCode(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS.getMessage(), ResultEnum.SUCCESS.getCode(), data);
    }

    public static <T> Result<T> failed() {
        return new Result<>(ResultEnum.FAILED.getMessage(), ResultEnum.FAILED.getCode(), null);
    }

    public static <T> Result<T> failed(String message) {
        return new Result<>(message, ResultEnum.FAILED.getCode(), null);
    }
}
