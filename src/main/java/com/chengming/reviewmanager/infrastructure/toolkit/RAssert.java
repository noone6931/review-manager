package com.chengming.reviewmanager.infrastructure.toolkit;

import com.chengming.reviewmanager.infrastructure.enums.ResultEnum;
import com.chengming.reviewmanager.infrastructure.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

public class RAssert {
    private RAssert() {
    }

    /**
     * 如果表达式是false直接返回rpcResponse
     *
     * @param expression 待检查表达式
     * @param errorInfo  已定义的errorInfo对象
     * @param consumer   单参数无返回值lambda,用于补充日志等
     * @param t          lambda参数
     * @param <T>        泛型
     */
    public static <T> void isTrue(boolean expression, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        if (!expression) {
            if (consumer != null) {
                consumer.accept(t);
            }
            throw new BusinessException(errorInfo);
        }
    }

    public static <T> void isTrue(boolean expression, String message, Consumer<T> consumer, T t) {
        if (!expression) {
            if (consumer != null) {
                consumer.accept(t);
            }
            throw new BusinessException(message);
        }
    }

    public static void isTrue(boolean expression, ResultEnum errorInfo) {
        isTrue(expression, errorInfo, null, null);
    }

    public static void isTrue(boolean expression, String message) {
        isTrue(expression, message, null, null);
    }


    public static <T> void isFalse(boolean expression, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        isTrue(!expression, errorInfo, consumer, t);
    }

    public static <T> void isFalse(boolean expression, String message, Consumer<T> consumer, T t) {
        isTrue(!expression, message, consumer, t);
    }

    public static void isFalse(boolean expression, String message) {
        isTrue(!expression, message);
    }

    public static void isFalse(boolean expression, ResultEnum errorInfo) {
        isTrue(!expression, errorInfo);
    }

    /**
     * @param text      待检查字符串,检查是否有空白及转义符等
     * @param errorInfo 已定义的errorInfo对象
     * @param consumer  单参数无返回值lambda,用于补充日志等
     * @param t         lambda参数
     * @param <T>       泛型
     */
    public static <T> void notBlank(String text, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        isFalse(StringUtils.isBlank(text), errorInfo, consumer, t);
    }

    public static <T> void notBlank(String text, String message, Consumer<T> consumer, T t) {
        isFalse(StringUtils.isBlank(text), message, consumer, t);
    }

    public static void notBlank(String text, ResultEnum errorInfo) {
        notBlank(text, errorInfo, null, null);
    }

    public static void notBlank(String text, String message) {
        notBlank(text, message, null, null);
    }


    /**
     * @param text      待检查字符串
     * @param errorInfo 已定义的errorInfo对象
     * @param consumer  单参数无返回值lambda,用于补充日志等
     * @param t         lambda参数
     * @param <T>       泛型
     */
    public static <T> void notEmpty(String text, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        isFalse(text == null || text.isEmpty(), errorInfo, consumer, t);
    }

    public static <T> void notEmpty(String text, String errorInfo, Consumer<T> consumer, T t) {
        isFalse(text == null || text.isEmpty(), errorInfo, consumer, t);
    }

    public static void notEmpty(String text, ResultEnum errorInfo) {
        notEmpty(text, errorInfo, null, null);
    }


    public static void notEmpty(String text, String message) {
        notEmpty(text, message, null, null);
    }


    /**
     * @param collection 待检查集合
     * @param errorInfo  已定义的errorInfo对象
     * @param consumer   单参数无返回值lambda,用于补充日志等
     * @param t          lambda参数
     * @param <T>        泛型
     */
    public static <T> void notEmpty(Collection collection, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        isFalse(CollectionUtils.isEmpty(collection), errorInfo, consumer, t);
    }

    public static <T> void notEmpty(Collection collection, String errorInfo, Consumer<T> consumer, T t) {
        isFalse(CollectionUtils.isEmpty(collection), errorInfo, consumer, t);
    }


    public static void notEmpty(Collection collection, ResultEnum errorInfo) {
        notEmpty(collection, errorInfo, null, null);
    }

    public static void notEmpty(Collection collection, String message) {
        notEmpty(collection, message, null, null);
    }


    /**
     * @param map       待校验map
     * @param errorInfo 已定义的errorInfo对象
     * @param consumer  单参数无返回值lambda,用于补充日志等
     * @param t         lambda参数
     * @param <T>       泛型
     */
    public static <T> void notEmpty(Map map, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        isFalse(CollectionUtils.isEmpty(map), errorInfo, consumer, t);
    }

    public static <T> void notEmpty(Map map, String errorInfo, Consumer<T> consumer, T t) {
        isFalse(CollectionUtils.isEmpty(map), errorInfo, consumer, t);
    }

    public static void notEmpty(Map map, ResultEnum errorInfo) {
        notEmpty(map, errorInfo, null, null);
    }

    public static void notEmpty(Map map, String message) {
        notEmpty(map, message, null, null);
    }


    /**
     * @param array     待检查数组
     * @param errorInfo 已定义的errorInfo对象
     * @param consumer  单参数无返回值lambda,用于补充日志等
     * @param t         lambda参数
     * @param <T>       泛型
     */
    public static <T> void notEmpty(Object[] array, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        isFalse(array == null || array.length == 0, errorInfo, consumer, t);
    }

    public static <T> void notEmpty(Object[] array, String errorInfo, Consumer<T> consumer, T t) {
        isFalse(array == null || array.length == 0, errorInfo, consumer, t);
    }

    public static void notEmpty(Object[] array, ResultEnum errorInfo) {
        notEmpty(array, errorInfo, null, null);
    }

    public static void notEmpty(Object[] array, String message) {
        notEmpty(array, message, null, null);
    }


    /**
     * @param object    待检查对象
     * @param errorInfo 已定义的errorInfo对象
     * @param consumer  单参数无返回值lambda,用于补充日志等
     * @param t         lambda参数
     * @param <T>       泛型
     */
    public static <T> void isNull(Object object, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        isTrue(object == null, errorInfo, consumer, t);
    }

    public static <T> void isNull(Object object, String errorInfo, Consumer<T> consumer, T t) {
        isTrue(object == null, errorInfo, consumer, t);
    }

    public static void isNull(Object object, ResultEnum errorInfo) {
        isNull(object, errorInfo, null, null);
    }

    public static void isNull(Object object, String message) {
        isNull(object, message, null, null);
    }

    /**
     * @param object    待检查对象
     * @param errorInfo 已定义的errorInfo对象
     * @param consumer  单参数无返回值lambda,用于补充日志等
     * @param t         lambda参数
     * @param <T>       泛型
     */
    public static <T> void notNull(Object object, ResultEnum errorInfo, Consumer<T> consumer, T t) {
        isFalse(object == null, errorInfo, consumer, t);
    }

    public static <T> void notNull(Object object, String errorInfo, Consumer<T> consumer, T t) {
        isFalse(object == null, errorInfo, consumer, t);
    }


    public static void notNull(Object object, ResultEnum errorInfo) {
        notNull(object, errorInfo, null, null);
    }

    public static void notNull(Object object, String message) {
        notNull(object, message, null, null);
    }
}
