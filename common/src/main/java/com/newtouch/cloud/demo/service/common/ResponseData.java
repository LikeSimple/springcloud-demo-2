package com.newtouch.cloud.demo.service.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = -502074009660099743L;
    private int errorCode;
    private String errorMessage;
    private boolean success;
    private T data;

    public ResponseData(T data) {
        this.errorCode = 0;
        this.errorMessage = "";
        this.success = true;
        this.data = data;
    }

    public ResponseData(int errorCode, String errorMessage) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = null;
    }

}
