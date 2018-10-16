package com.baseljc.mvp.networking.exception;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:    服务器约定返回的错误，统一解析
 *                  服务器返回结果，code不为0000时，统一抛出这个类型的异常
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/12/2 09:57
 */
public class HttpResultException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    private String codeDesc;

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
        if (message == null)
            message = "";
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpResultException(String message) {
        this(0, message);
    }

    public HttpResultException(int resultCode, String message) {
        this(resultCode, "", message);
    }

    public HttpResultException(int resultCode, String codeDesc, String message) {
        super(getHttpExceptionMessage(resultCode, message));
        this.code = resultCode;
        this.codeDesc = codeDesc;
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
                msg = message != null && message.length() > 0 ? message : "未知错误";
        }
        return msg;
    }


    public String getCodeDesc() {
        if (codeDesc == null)
            codeDesc = "";
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
