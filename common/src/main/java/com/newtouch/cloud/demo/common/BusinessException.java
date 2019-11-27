package com.newtouch.cloud.demo.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.logging.Level;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -7475173755468280533L;

    private int code = SysResponseEnum.SUCCESS.getCode();

    /**
     * 提示消息
     */
    private String message;

    /**
     * 返回数据
     */
    private String data;

    /**
     * 消息级别
     */
    private Level level = Level.INFO;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, Level level) {
        super(message);
        this.message = message;
        this.level = level;
    }

    public BusinessException(String message, String data, Level level) {
        super(message);
        this.message = message;
        this.data = data;
        this.level = level;
    }

    public BusinessException(int code, String message, String data, Level level) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.level = level;
    }
}
