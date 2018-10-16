package com.baseljc.mvp.dagger.components;

import android.app.Application;


import com.baseljc.mvp.dagger.modules.AndroidModule;
import com.baseljc.mvp.dagger.modules.ApplicationModule;
import com.baseljc.mvp.dagger.modules.NetworkModule;
import com.baseljc.mvp.networking.BaseUrlInterceptor;
import com.baseljc.mvp.util.other.PropertiesManager;
import com.baseljc.mvp.util.testing.ForTestingPurposes;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by linet on 2017/8/21.
 */

@Singleton
//@Component(modules = {ApplicationModule.class, NetworkModule.class, AndroidModule.class, BuildTypeAwareModule.class})
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        AndroidModule.class})
public interface ApplicationComponent {

    Application application();

//    ImageLoader imageLoader();

    void inject(Application application);

    @ForTestingPurposes
    BaseUrlInterceptor baseUrlInterceptor();

//     @ForTestingPurposes
//     OkReplayInterceptor okReplayInterceptor();

    Retrofit retrfit();

    PropertiesManager propertiesManager();
    OkHttpClient okHttpClient();

}
