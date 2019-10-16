package com.example.annotation.exception;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/23 20:12
 * @Version 1.0
 */
public class ResultVo<T> {
    private String resultCode;
    private String resultMsg;
    private T data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
