package io.github.flyhero.springboot.plus.model;

/**
 * 错误枚举类需实现此类
 */
public interface Errors {
    int code();
    String msg();
}
