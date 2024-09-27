package com.chengming.reviewmanager.infrastructure.controller;

import com.chengming.reviewmanager.interfaces.dto.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    // 处理通用的异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleAllExceptions(Exception ex) {
        log.error("Exception: ", ex);
        return Result.failed(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return Result.failed(errors.toString());
    }
}
