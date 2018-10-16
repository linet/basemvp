package com.baseljc.mvp.networking.exception;

import android.text.TextUtils;

/**
 * @author: liupeiyang
 * @Filename:
 * @Description:    服务器约定返回的错误，统一解析-java接口服务
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date:2018/05/14  09:57
 */
public class JavaHttpResultException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JavaHttpResultException(String message) {
        super(message);
    }

    public JavaHttpResultException(int resultCode, String message) {
        this(getHttpExceptionMessage(resultCode, message));
        this.code = resultCode;
        this.message = message;
    }


    /**
     * 错误码解析
     * @param code
     * @return
     */
    private static String getHttpExceptionMessage(int code, String message){
        String msg;
        switch (code) {
            default:
                msg = !TextUtils.isEmpty(message) ? message : "未知错误";
        }
        return msg;
    }


}
