package com.baseljc.mvp.dagger.modules;

import android.app.Application;
import android.content.res.AssetManager;


import com.baseljc.mvp.util.other.PropertiesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by linet on 2017/8/21.
 */

@Module
public class ApplicationModule {

    private final Application app;


    public ApplicationModule(Application app) {
        this.app = app;
    }

    @Provides
    public Application providesApplication() {
        return app;
    }


    @Provides
    @Singleton
    public static PropertiesManager providesPropertyManager(AssetManager assetManager) {
        return PropertiesManager.share(assetManager);
    }

//    @Provides
//    @Singleton
//    public static ImageLoader providesImageLoader(Application application) {
//        return new ImageLoader(application.getApplicationContext());
//    }

}
