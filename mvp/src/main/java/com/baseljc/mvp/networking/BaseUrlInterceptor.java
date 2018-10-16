package com.baseljc.mvp.networking;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by linet on 2017/8/22.
 */

public class BaseUrlInterceptor implements Interceptor {

    @Nullable
    private volatile String host;

    private final String realBaseUrl;

    public BaseUrlInterceptor(String realBaseUrl) {
        this.realBaseUrl = realBaseUrl;
    }

    public void setBaseUrl(String host) {
        this.host = host;
    }

    public void resetBaseUrl() {
        this.host = realBaseUrl;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (host != null && !realBaseUrl.equals(host)) {
            @Nullable HttpUrl newUrl = HttpUrl.parse(host);
            request = request.newBuilder()
                    .url(newUrl)
                    .build();
        }
        return chain.proceed(request);
    }
}
