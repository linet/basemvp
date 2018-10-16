package com.baseljc.mvp.dagger.modules;

import android.app.Application;


import com.baseljc.mvp.dagger.converter.ResponseConvertFactory;
import com.baseljc.mvp.networking.AuthenticationInterceptor;
import com.baseljc.mvp.networking.BaseUrlInterceptor;
import com.baseljc.mvp.networking.GlobalValue;
import com.baseljc.mvp.util.https.HttpsUtils;
import com.baseljc.mvp.util.other.PropertiesManager;
import com.baseljc.mvp.util.testing.ForTestingPurposes;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by linet on 2017/8/22.
 */


@Module
public final class NetworkModule {

    private NetworkModule() {

        throw new AssertionError();
    }
//    /**
//     * For release variant an empty list of interceptors is injected into OkHttp. In debug and qa builds an actual interceptor is injected
//     * so that Stetho can work. See the debug version of this file under /debug.
//     */
//    @Provides
//    public static List<Interceptor> provideOkHttpNetworkInterceptors() {
//        return Collections.emptyList();
//    }


    @Provides
    @Singleton
    public static HttpLoggingInterceptor providesHttpLoggingInterceptor(PropertiesManager propertiesManager) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (propertiesManager.getBuildConfig()) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return interceptor;
    }

    @Provides
    @Singleton
    @ForTestingPurposes
    public static BaseUrlInterceptor providesBaseUrlInterceptor(PropertiesManager propertiesManager) {
        return new BaseUrlInterceptor(propertiesManager.getBaseUrl());
    }

//    @Provides
//    @Singleton
//    public static OkReplayInterceptor provideOkReplayInterceptor() {
//        return new OkReplayInterceptor();
//    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(PropertiesManager propertiesManager,
                                                   HttpLoggingInterceptor httpLoggingInterceptor,
//                                                   List<Interceptor> networkInterceptors,
                                                   BaseUrlInterceptor baseUrlInterceptor,
                                                   Application application) {

//    public static OkHttpClient provideOkHttpClient(PropertiesManager propertiesManager,
// HttpLoggingInterceptor httpLoggingInterceptor,
//                                                   List<Interceptor> networkInterceptors,
// BaseUrlInterceptor baseUrlInterceptor,
//                                                   Application application,
// OkReplayInterceptor okReplayInterceptor) {

        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();


        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS);
        okHttpBuilder.writeTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS);
        okHttpBuilder.readTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS);
//        okHttpBuilder.retryOnConnectionFailure(true);

        // Adds authentication headers when required in network calls
        okHttpBuilder.addNetworkInterceptor(new AuthenticationInterceptor(propertiesManager));

        // Helps with changing base url of network calls in espresso tests to the MockWebServer base url.
        okHttpBuilder.addInterceptor(baseUrlInterceptor);

        // For https://github.com/airbnb/okreplay library, recording and replaying server responses.
//        okHttpBuilder.addInterceptor(okReplayInterceptor);

        // For release builds nothing is added, the list is empty. For debug builds Stetho interceptor is added.
//        for (Interceptor networkInterceptor : networkInterceptors) {
//            okHttpBuilder.addNetworkInterceptor(networkInterceptor);
//        }

        // Displaying all network calls within the app through a notification. Debug builds only. See https://github.com/jgilfelt/chuck
//        if (!TestUtil.areRobolectricTestsRunning()) { // Robolectric doesn't like this library
//            okHttpBuilder.addInterceptor(new ChuckInterceptor(application.getApplicationContext()));
//        }
        //添加头部信息
        okHttpBuilder.addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Accept", "*/*")
                    .addHeader("syt_app_t", "android")
//                    .addHeader("syt_app_v", AppUtils.getVersionName(application))
                    .addHeader("syt_m_id", GlobalValue.MB_ID)
                    .addHeader("syt_app_c", android.os.Build.SERIAL==null?"":android.os.Build.SERIAL)
                    .build();
            return chain.proceed(request);
        });

        // Logs network calls for debug builds
        okHttpBuilder.addInterceptor(httpLoggingInterceptor);
        if (GlobalValue.BASE_API_URL.equals(GlobalValue.BASE_API_URL_RELEASED)) {//正式环境
            try {
                //添加https 证书
                HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{new Buffer().writeUtf8(GlobalValue.SIGN_INFO).inputStream()}, null, null);
                okHttpBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
            okHttpBuilder.hostnameVerifier((hostname, session) -> true);
        }
        return okHttpBuilder.build();
    }

    private static final long TIMEOUT_CONNECT = 60 * 1000;

    @Provides
    @Singleton
    public static Retrofit providesRetrofit(OkHttpClient okHttpClient, PropertiesManager propertiesManager, Application application) {

        return new Retrofit.Builder()
                .baseUrl(propertiesManager.getBaseUrl())
                .validateEagerly(propertiesManager.getBuildConfig())// Fail early: check Retrofit configuration at creation time in Debug build.
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ResponseConvertFactory.create(application))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

}