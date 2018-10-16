package com.baseljc.mvp.networking.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

//import retrofit2.adapter.rxjava.HttpException;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description: 统一请求错误处理类
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/12/2 10:45
 */
public class ExceptionEngine {

    /**
     * 约定异常__未知错误
     */
    public static final int UNKNOWN = 5000;
    /**
     * 约定异常__解析错误
     */
    public static final int PARSE_ERROR = UNKNOWN + 1;
    /**
     * 约定异常__网络错误，属于连接失败
     */
    public static final int NETWORD_ERROR = PARSE_ERROR + 1;
    /**
     * 约定异常__没有网络
     */
    public static final int NETWORD_NOERROR = NETWORD_ERROR + 1;
    /**
     * 约定异常__请求超时
     */
    public static final int HTTP_TIMEOUT = NETWORD_NOERROR + 1;
    /**
     * 约定异常__请求参数错误
     */
    public static final int HTTP_ERROR = HTTP_TIMEOUT + 1;


    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    // 处理相应的异常状态，并且进行本地转换
    public static ApiException handleException(Throwable e) {
        ApiException ex;


        if (e instanceof ConnectException) {
            //连接失败
            ex = new ApiException(e, NETWORD_ERROR);
//            ex.setMessage("连接失败");
            ex.setMessage("网络不稳定，请稍候再试！");

            return ex;
        }

        if (e instanceof SocketTimeoutException) {
            //请求超时
            ex = new ApiException(e, HTTP_TIMEOUT);
            ex.setMessage("请求超时");
            return ex;
        }

        //HTTP错误，服务器层的异常，还没有到接口层
        if (e instanceof HttpException) {
            //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    ex.setMessage("请求参数错误");  //均视为网络错误
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
//                    ex.setMessage("其他错误");  //均视为网络错误
                    ex.setMessage("网络不稳定，请稍候再试！");  //均视为网络错误
                    break;
            }
            return ex;
        }

        //接口处理返回异常，returncode不为0000，本地统一封装的异常处理类HttpResultException
        if (e instanceof HttpResultException) {
            //服务器约定返回的错误
            HttpResultException resultException = (HttpResultException) e;
            ex = new ApiException(resultException, resultException.getCode(), resultException.getCodeDesc());
            ex.setMessage(resultException.getMessage());
            return ex;
        }

        //数据转换异常
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //均视为解析错误
            ex = new ApiException(e, PARSE_ERROR);
            ex.setMessage("解析错误");
            return ex;
        }



        ex = new ApiException(e, UNKNOWN);
//            ex.setMessage("未知错误");          //未知错误
        ex.setMessage("页面异常，请稍候再试！");          //未知错误
        return ex;
    }
}
