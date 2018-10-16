package com.baseljc.mvp.networking.model;

/**
 * Created by linet on 16/9/22.
 */

public class ResultModel<T> extends BaseResultModel {

    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
