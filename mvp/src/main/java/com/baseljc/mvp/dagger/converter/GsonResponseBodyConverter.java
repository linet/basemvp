package com.baseljc.mvp.dagger.converter;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.baseljc.mvp.networking.model.ResultModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Application application;
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Application application, Gson gson, TypeAdapter<T> adapter) {
        this.application = application;
        this.gson = gson;
        this.adapter = adapter;
    }

//    /**
//     * 返回数据统一处理
//     *
//     * @param value ResponseBody
//     * @return value 返回异常
//     * @throws IOException 返回数据统一处理
//     */
//    @Override
//    public T convert(ResponseBody value) throws IOException {
//        try {
//            JSONObject object = new JSONObject(value.string());
////            JSONObject object = new JSONObject("{code:9996,data:\"\"}");
//            ResponseExceptionUtils exceptionUtils = new ResponseExceptionUtils(object.toString(), gson);
//            exceptionUtils.parseErrorJsonString();
//            return adapter.fromJson(object.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            value.close();
//        }
//        return null;
//    }

    /**
     * 返回数据统一处理
     *
     * @param value ResponseBody
     * @return value 返回异常
     * @throws IOException 返回数据统一处理
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T resultModel = adapter.read(jsonReader);
            if (resultModel instanceof ResultModel && application instanceof IAppResultCodeCheck) {
                ((IAppResultCodeCheck)application).checkCode((ResultModel)resultModel);
            }
            return resultModel;
        } finally {
            value.close();
        }
    }
}