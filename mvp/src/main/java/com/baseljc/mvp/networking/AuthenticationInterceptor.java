package com.baseljc.mvp.networking;

import com.baseljc.mvp.util.other.PropertiesManager;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by linet on 2017/8/22.
 */

public class AuthenticationInterceptor implements Interceptor {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE_WITH_PLACEHOLDER = "Bearer %s";

    private static final String AUTHORIZATION_FAKE_HEADER_KEY = "Authentication";
    private static final String AUTHORIZATION_FAKE_HEADER_VALUE = ": True";

    /**
     * Fake authorization header. Only added so that this interceptor replaces it with the correct auth key.
     */
    public static final String DO_AUTHENTICATION = AUTHORIZATION_FAKE_HEADER_KEY + AUTHORIZATION_FAKE_HEADER_VALUE;

    private final PropertiesManager propertiesManager;

    @Inject
    public AuthenticationInterceptor(PropertiesManager propertiesManager) {
        Timber.d("------------->AuthenticationInterceptor=");
        this.propertiesManager = propertiesManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("client", "app").build();
        Timber.d("------------->AuthenticationInterceptor.intercept=");
//        if (request.header(AUTHORIZATION_FAKE_HEADER_KEY) != null) {
////            Timber.d("Secure network request encountered. Adding authentication headers.");
//
//            request = request.newBuilder()
//                    .addHeader(AUTHORIZATION_HEADER_KEY, String.format(AUTHORIZATION_HEADER_VALUE_WITH_PLACEHOLDER, propertiesManager.getDribleClientAccessToken()))
//                    .build();
//
//
////            builder.addHeader(AUTHORIZATION_HEADER_KEY
////                    , String.format(AUTHORIZATION_HEADER_VALUE_WITH_PLACEHOLDER
////                            , propertiesManager.getDribleClientAccessToken()));
//        }
        request = builder.build();
        return chain.proceed(request);
    }
}
