package com.baseljc.mvp.networking;

import com.baseljc.mvp.networking.exception.ExceptionEngine;
import com.baseljc.mvp.networking.model.ResultModel;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TransformerUtils {



    /**
     * 统一线程处理
     */
    public static <T> SingleTransformer<T, T> switchSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 统一请求错误处理
     */
    public static <T> Function<Throwable, SingleSource<T>> applyHttpResponeFunc(){
        return throwable -> Single.error(ExceptionEngine.handleException(throwable));
//        return throwable -> Observable.error(ExceptionEngine.handleException(throwable));
    }

    /**
     * 一般请求转换器，v1.3.0版本的API返回数据模型
     */
    public static <T> SingleTransformer<ResultModel<T>, T> applyJavaSimpleTransformer() {
        return httpResultObservable -> httpResultObservable
                .map(applyJavaHttpResultFunc())                       //后台约定错误处理
                .onErrorResumeNext(applyHttpResponeFunc())        //请求错误处理
                .compose(TransformerUtils.<T>switchSchedulers());
    }


    /**
     * 统一后台约定错误处理，v1.3.0版本的API返回数据模型
     */
    public static <T> Function<ResultModel<T>, T> applyJavaHttpResultFunc() {
        return httpResult -> {
//            if (!"0000".equals(httpResult.getReturnCode())) {
//                //当返回结果的code不为0000时，抛出结果处理异常，HttpResultException
//                //这里对returncode的类型进行转换，有原来返回的字符串转为数字
//                throw new HttpResultException(Integer.valueOf(httpResult.getReturnCode()), httpResult.getCodeDesc(), httpResult.getMsg());
//            }
            return httpResult.getData();
        };
    }
}
