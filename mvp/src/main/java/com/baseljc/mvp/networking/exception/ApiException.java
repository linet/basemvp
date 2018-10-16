package com.baseljc.mvp.networking.exception;

import android.text.TextUtils;

/**
 * @author: liupeiyang
 * @Filename:
 * @Description: 统一异常类
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2014/4/10 10:45
 */
public class ApiException extends Exception {

    /**
     * 错误码
     */
    private int code;

    private String codeDesc;

    /**
     * 错误信息
     */
    private String message;

    public ApiException(Throwable throwable, int code) {
        this(throwable, code, "");
    }

    public ApiException(Throwable throwable, int code, String codeDesc) {
        super(throwable);
        this.code = code;
        this.codeDesc = codeDesc;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        if (TextUtils.isEmpty(message)) {
            message = getCodeDesc();
        }
        if (message == null) {
            message = "";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCodeDesc() {
        if (codeDesc == null) {
            codeDesc = "";
        }
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
